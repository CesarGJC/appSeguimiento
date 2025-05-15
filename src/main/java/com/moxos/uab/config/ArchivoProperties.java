package com.moxos.uab.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "archivo")
public class ArchivoProperties {
    private String directorioBase;
    private String raizSegura;
    private List<String> palabrasProhibidas = new ArrayList<>();
    private String nombreRegex;
    private List<String> extensionesPermitidasImagenes = new ArrayList<>();
    private List<String> extensionesPermitidasDocumentos = new ArrayList<>();
    private List<String> mimeImagenesPermitidas = new ArrayList<>();
    private List<String> mimeDocumentosPermitidos = new ArrayList<>();
}
