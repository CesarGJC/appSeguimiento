package com.moxos.uab.business.service.impl;

import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Set;

@Service
public class ArchivoService {
    private static final Set<String> EXTENSIONES_PERMITIDAS_IMAGENES = Set.of("jpg", "png", "jpeg", "doc");
    private static final Set<String> TIPOS_MIME_PERMITIDOS_IMAGENES = Set.of("image/jpeg", "image/png");
    private static final Set<String> EXTENSIONES_PERMITIDAS_DOCUMENTOS = Set.of("pdf", "txt", "doc", "docx", "xlsx", "xls");
    private static final Set<String> TIPOS_MIME_PERMITIDOS_DOCUMENTOS = Set.of("application/pdf", "text/plain", "application/x-tika-ooxml", "application/vnd.ms-excel", "application/vnd.ms-office", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    private static final Tika TIKA = new Tika();
    @Value("${app.upload.path}") // Directorio seguro
    private String directorioBase;

    public Response<String> guardarArchivo(MultipartFile archivo, String nombreArchivo, String directorio, String tipoDocumento) throws IOException {
        var response = validarArchivo(archivo, tipoDocumento);
        if (!response.isSuccess())
            return new Response<>(false, response.getMessage(), "");

        String extension = obtenerExtension(archivo.getOriginalFilename());
        String directorioDocumento = crearDirectorio(directorio);
        // Normalizar nombre de archivo
        String nombreArchivoSeguro = Objects.requireNonNull(String.format("%s.%s", nombreArchivo, extension)).replaceAll("[^a-zA-Z0-9.\\-_]", "_");

        Path rutaDestino = Paths.get(directorioDocumento).resolve(nombreArchivoSeguro).normalize();
        Files.copy(archivo.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);

        return new Response<>(true, "", nombreArchivoSeguro);
    }

    private GeneralResponse validarArchivo(MultipartFile archivo, String tipoDocumento) throws IOException {
        if (archivo.isEmpty()) {
            return new GeneralResponse(false, "El archivo está vacío o el archivo está corrupto.");
        }

        String nombreOriginal = archivo.getOriginalFilename();
        if (nombreOriginal == null || nombreOriginal.contains("..")) {
            return new GeneralResponse(false, "Nombre de archivo inválido.");
        }

        // Validar extensión
        String extension = obtenerExtension(nombreOriginal);
        if (tipoDocumento.equals("image")) {
            if (!EXTENSIONES_PERMITIDAS_IMAGENES.contains(extension)) {
                return new GeneralResponse(false, "Extensión de imagenes no permitida.");
            }
            // Validar tipo MIME
            String tipoMimeReal = TIKA.detect(archivo.getInputStream());
            if (!TIPOS_MIME_PERMITIDOS_IMAGENES.contains(tipoMimeReal)) {
                return new GeneralResponse(false, "Tipo de imagenes no permitido.");
            }
        }
        if (tipoDocumento.equals("documento")) {
            if (!EXTENSIONES_PERMITIDAS_DOCUMENTOS.contains(extension)) {
                return new GeneralResponse(false, "Extensión de documento no permitida debe subir imagenes en word excel txt.");
            }
            // Validar tipo MIME
            String tipoMimeReal = TIKA.detect(archivo.getInputStream());
            if (!TIPOS_MIME_PERMITIDOS_DOCUMENTOS.contains(tipoMimeReal)) {
                return new GeneralResponse(false, "Tipo de documento no permitido  debe subir imagenes en word excel txt.");
            }
        }

        return new GeneralResponse(true, "");
    }

    private String obtenerExtension(String nombreArchivo) {
        int index = nombreArchivo.lastIndexOf('.');
        return (index == -1) ? "" : nombreArchivo.substring(index + 1).toLowerCase();
    }

    private String crearDirectorio(String nombreDirectorio) throws IOException {
        String directorio = String.format("%s/investigacion/%s", directorioBase, nombreDirectorio);
        Path path = Paths.get(directorio);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        return directorio;
    }
}
