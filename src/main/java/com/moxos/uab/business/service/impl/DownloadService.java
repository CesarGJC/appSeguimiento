package com.moxos.uab.business.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class DownloadService {
    @Value("${app.upload.path}") // Directorio seguro
    private String directorioBase;

    public void downloadResource(String fileName, String nameResource, String directorio, HttpServletResponse response) throws IOException {
        String fileResource = String.format("%s/investigacion/%s/%s", directorioBase, directorio, fileName);
        File file = new File(fileResource);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "El archivo no existe.");
            return;
        }
        // Configurar el tipo de contenido
        String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
        if (contentType == null) {
            contentType = "application/octet-stream"; // Tipo de archivo genérico
        }
        response.setContentType(contentType);

        // Configurar encabezado para forzar la descarga
        String nameFileName = String.format("attachment; filename=\"%s\"", nameResource);
        response.setHeader("Content-Disposition", nameFileName);
        response.setContentLength((int) file.length());

        // Enviar el archivo al cliente
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
        }
    }
}
