package com.moxos.uab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileStorageConfig {

    @Bean
    public Path baseFilePath(ArchivoProperties archivoProperties) throws IOException {
        Path basePath = Paths.get(archivoProperties.getDirectorioBase()).toAbsolutePath().normalize();
        Path rootPath = Paths.get(archivoProperties.getRaizSegura()).toAbsolutePath().normalize();

        if (!basePath.startsWith(rootPath)) {
            throw new IllegalArgumentException("Directorio fuera de la raíz segura.");
        }

        Files.createDirectories(basePath);
        return basePath;
    }
}