package com.moxos.uab.business.service.impl;

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
public class ArchivoServiceImpl {
    private static final Set<String> EXTENSIONES_PERMITIDAS = Set.of("jpg", "png", "pdf", "txt", "jpeg", "doc", "docx", "xlsx", "xls");
    private static final Set<String> TIPOS_MIME_PERMITIDOS = Set.of("image/jpeg", "image/png", "application/pdf", "text/plain", "application/vnd.ms-excel", "application/vnd.ms-office", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    private static final Tika TIKA = new Tika();
    @Value("${app.upload.path:/investigacion}") // Directorio seguro
    private String directorioBase;

    public String guardarArchivo(MultipartFile archivo, String nombreArchivo, String directorio) throws IOException {
        validarArchivo(archivo);

        // Normalizar nombre de archivo
        String nombreArchivoSeguro = Objects.requireNonNull(archivo.getOriginalFilename()).replaceAll("[^a-zA-Z0-9.\\-_]", "_");

        Path rutaDestino = Paths.get(String.format("%s/%s", directorioBase, directorio)).resolve(nombreArchivoSeguro).normalize();
        Files.copy(archivo.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);

        return STR."Archivo guardado en: \{rutaDestino.toString()}";
    }

    private void validarArchivo(MultipartFile archivo) throws IOException {
        if (archivo.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío.");
        }

        String nombreOriginal = archivo.getOriginalFilename();
        if (nombreOriginal == null || nombreOriginal.contains("..")) {
            throw new IllegalArgumentException("Nombre de archivo inválido.");
        }

        // Validar extensión
        String extension = obtenerExtension(nombreOriginal);
        if (!EXTENSIONES_PERMITIDAS.contains(extension)) {
            throw new IllegalArgumentException("Extensión de archivo no permitida.");
        }

        // Validar tipo MIME
        String tipoMimeReal = TIKA.detect(archivo.getInputStream());
        if (!TIPOS_MIME_PERMITIDOS.contains(tipoMimeReal)) {
            throw new IllegalArgumentException("Tipo de archivo no permitido.");
        }
    }

    private String obtenerExtension(String nombreArchivo) {
        int index = nombreArchivo.lastIndexOf('.');
        return (index == -1) ? "" : nombreArchivo.substring(index + 1).toLowerCase();
    }
}
