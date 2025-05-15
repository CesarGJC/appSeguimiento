package com.moxos.uab.business.service.impl;

import com.moxos.uab.config.ArchivoProperties;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class PhotoStorageService {

    private final ArchivoProperties archivoProperties;
    private final ImageCompressionService imageCompressionService;

    public record Resultado(Resource resource, byte[] toBytes) {
    }

    // ---------- API Pública ----------

    public byte[] byteResource(String nombreArchivo) {
        return byteResource(null, nombreArchivo);
    }

    public byte[] byteResource(String nombreArchivo, int width, int height, float quality) {
        return byteResource(null, nombreArchivo, width, height, quality);
    }

    public byte[] byteResource(String directorioImage, String nombreArchivo) {
        Path rutaArchivo = resolveArchivoSeguro(directorioImage, nombreArchivo);
        return readBytes(rutaArchivo);
    }

    public byte[] byteResource(String directorioImage, String nombreArchivo, int width, int height, float quality) {
        Path rutaArchivo = resolveArchivoSeguro(directorioImage, nombreArchivo);
        return compress(rutaArchivo, width, height, quality).toBytes();
    }

    public Resource download(String nombreArchivo) throws MalformedURLException {
        return download(null, nombreArchivo);
    }

    public Resource download(String nombreArchivo, int width, int height, float quality) {
        return download(null, nombreArchivo, width, height, quality);
    }

    public Resource download(String directorioImage, String nombreArchivo) throws MalformedURLException {
        Path rutaArchivo = resolveArchivoSeguro(directorioImage, nombreArchivo);
        return new UrlResource(rutaArchivo.toUri());
    }

    public Resource download(String directorioImage, String nombreArchivo, int width, int height, float quality) {
        Path rutaArchivo = resolveArchivoSeguro(directorioImage, nombreArchivo);
        return compress(rutaArchivo, width, height, quality).resource();
    }

    // ---------- Lógica Interna ----------

    private Path resolveArchivoSeguro(String directorioImage, String nombreArchivo) {
        Path directorioBase = resolveDirectorio(directorioImage);
        Path directorioRaiz = Paths.get(archivoProperties.getRaizSegura());
        Path archivoDestino = directorioBase.resolve(nombreArchivo).normalize();

        if (!archivoDestino.startsWith(directorioRaiz)) {
            throw new SecurityException("Acceso no autorizado fuera del directorio base.");
        }

        if (!Files.exists(archivoDestino) || !Files.isReadable(archivoDestino)) {
            archivoDestino = directorioBase.resolve("nulo.png").normalize();
        }

        return archivoDestino;
    }

    private Path resolveDirectorio(String directorioImage) {
        if (directorioImage == null || directorioImage.isBlank()) {
            return Paths.get(archivoProperties.getDirectorioBase()).toAbsolutePath().normalize();
        }
        return Paths.get(archivoProperties.getDirectorioBase() + File.separator + directorioImage).toAbsolutePath().normalize();
    }

    private byte[] readBytes(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer bytes del archivo", e);
        }
    }

    private Resultado compress(Path imagePath, int width, int height, float quality) {
        try (InputStream inputStream = Files.newInputStream(imagePath)) {
            byte[] compressedBytes = imageCompressionService.compressImage(inputStream, width, height, quality);
            return new Resultado(new ByteArrayResource(compressedBytes), compressedBytes);
        } catch (IOException e) {
            throw new RuntimeException("Error al comprimir imagen", e);
        }
    }
}
