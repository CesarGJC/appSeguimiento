package com.moxos.uab.business.service.impl;

import com.moxos.uab.config.ArchivoProperties;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class ArchivoService {

    private final ArchivoProperties archivoProperties;
    private final Tika tika = new Tika();
    private static final Pattern INVALID_FILENAME_CHARS = Pattern.compile("[^a-zA-Z0-9.\\-_]");
    private static final int BUFFER_SIZE = 1024;

    public Response<String> save(MultipartFile archivo, String nombreArchivo, String directorio, String tipoDocumento) throws IOException {
        var validacion = validarArchivo(archivo, tipoDocumento);
        if (!validacion.isSuccess()) return new Response<>(false, validacion.getMessage(), "");

        String extension = obtenerExtension(Objects.requireNonNull(archivo.getOriginalFilename()));
        String nombreSeguro = generarNombreSeguro(nombreArchivo, extension);

        var dirResponse = crearDirectorioSeguro(directorio);
        if (!dirResponse.isSuccess()) return new Response<>(false, dirResponse.getMessage(), "");

        var rutaValidada = validarPathSeguro(nombreSeguro, dirResponse.getResult());
        if (!rutaValidada.isSuccess()) return new Response<>(false, rutaValidada.getMessage(), "");

        Files.copy(archivo.getInputStream(), rutaValidada.getResult(), StandardCopyOption.REPLACE_EXISTING);

        return new Response<>(true, "", nombreSeguro);
    }

    public void download(String fileName, String nameResource, String directorio, HttpServletResponse response) {
        try {
            Path baseDir = construirDirectorio(directorio);
            var recursoResp = getResourceSeguro(fileName, baseDir);

            if (!recursoResp.isSuccess()) {
                log.error(recursoResp.getMessage());
                response.sendError(HttpServletResponse.SC_NOT_FOUND, recursoResp.getMessage());
                return;
            }

            File archivo = recursoResp.getResult().getFile();
            String contentType = Optional.ofNullable(Files.probeContentType(archivo.toPath()))
                    .orElse("application/octet-stream");

            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nameResource + "\"");
            response.setContentLength((int) archivo.length());

            try (InputStream is = new FileInputStream(archivo);
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            }
        } catch (IOException e) {
            log.error("Error al descargar archivo: {}", e.getMessage(), e);
        }
    }

    // ------------------ MÉTODOS INTERNOS ------------------

    private GeneralResponse validarArchivo(MultipartFile archivo, String tipoDocumento) throws IOException {
        if (archivo.isEmpty()) return new GeneralResponse(false, "El archivo está vacío.");

        String originalName = archivo.getOriginalFilename();
        if (originalName == null || originalName.contains(".."))
            return new GeneralResponse(false, "Nombre de archivo inválido.");

        byte[] bytes = archivo.getBytes();
        String contenido = new String(bytes, StandardCharsets.UTF_8);

        if (contienePalabrasProhibidas(contenido))
            return new GeneralResponse(false, "El archivo contiene contenido prohibido.");

        String extension = obtenerExtension(originalName);
        String mime = tika.detect(bytes);

        if (!validarExtension(extension, tipoDocumento))
            return new GeneralResponse(false, "Extensión no permitida para " + tipoDocumento + ".");

        if (!validarMime(mime, tipoDocumento))
            return new GeneralResponse(false, "Tipo MIME no permitido para " + tipoDocumento + ".");

        return new GeneralResponse(true, "");
    }

    private boolean contienePalabrasProhibidas(String contenido) {
        return archivoProperties.getPalabrasProhibidas().stream()
                .anyMatch(p -> contenido.toUpperCase().contains(p.toUpperCase()));
    }

    private boolean validarExtension(String ext, String tipo) {
        return switch (tipo) {
            case "image" -> archivoProperties.getExtensionesPermitidasImagenes().contains(ext);
            case "documento" -> archivoProperties.getExtensionesPermitidasDocumentos().contains(ext);
            default -> false;
        };
    }

    private boolean validarMime(String mime, String tipo) {
        return switch (tipo) {
            case "image" -> archivoProperties.getMimeImagenesPermitidas().contains(mime);
            case "documento" -> archivoProperties.getMimeDocumentosPermitidos().contains(mime);
            default -> false;
        };
    }

    private String obtenerExtension(String nombreArchivo) {
        int index = nombreArchivo.lastIndexOf('.');
        return (index == -1) ? "" : nombreArchivo.substring(index + 1).toLowerCase();
    }

    private String generarNombreSeguro(String nombre, String extension) {
        String combinado = nombre + "." + extension;
        return INVALID_FILENAME_CHARS.matcher(combinado).replaceAll("_");
    }

    private Path construirDirectorio(String nombre) {
        return Paths.get(archivoProperties.getDirectorioBase(), "investigacion", nombre).toAbsolutePath().normalize();
    }

    private Response<Path> crearDirectorioSeguro(String nombreDirectorio) throws IOException {
        Path destino = construirDirectorio(nombreDirectorio);
        Path raiz = Paths.get(archivoProperties.getRaizSegura()).toAbsolutePath().normalize();

        if (!destino.startsWith(raiz)) {
            return new Response<>(false, "Acceso no autorizado al crear directorio.", null);
        }

        if (!Files.exists(destino)) {
            Files.createDirectories(destino);
        }

        return new Response<>(true, "", destino);
    }

    private Response<Path> validarPathSeguro(String nombreArchivo, Path directorio) {
        Path raiz = Paths.get(archivoProperties.getRaizSegura()).toAbsolutePath().normalize();
        Path destino = directorio.resolve(nombreArchivo).normalize();

        if (!destino.startsWith(raiz)) {
            return new Response<>(false, "Acceso no autorizado al path.", null);
        }

        return new Response<>(true, "", destino);
    }

    private Response<Resource> getResourceSeguro(String nombreArchivo, Path directorio) throws MalformedURLException {
        Path raiz = Paths.get(archivoProperties.getRaizSegura()).toAbsolutePath().normalize();
        Path archivoDestino = directorio.resolve(nombreArchivo).normalize();

        if (!archivoDestino.startsWith(raiz)) {
            return new Response<>(false, "Acceso no autorizado a archivos fuera del directorio permitido", null);
        }

        Resource recurso = new UrlResource(archivoDestino.toUri());
        if (!recurso.exists() || !recurso.isReadable()) {
            return new Response<>(false, "No se puede leer el archivo: " + nombreArchivo, null);
        }

        return new Response<>(true, "", recurso);
    }
}
