package com.moxos.uab.config.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.header.HeaderWriter;

public class NonceHeaderWriter implements HeaderWriter {

    @Override
    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
        // Obtener el nonce de la solicitud
        String nonce = (String) request.getAttribute("nonce");

        // Configurar el encabezado CSP con el nonce
        if (nonce != null) {
            String cspPolicy = police(nonce);
            response.setHeader("Content-Security-Policy", cspPolicy);
        }
    }
    private String police(String nonce) {
        StringBuilder policyBuilder = new StringBuilder();
        policyBuilder.append("default-src 'self';")
                .append("base-uri 'self';")
                .append("font-src 'self' https: data:;")
                .append("form-action 'self';")
                .append("frame-ancestors 'self';")
                .append("img-src 'self' data:;")
                .append("object-src 'none';")
                .append(String.format("script-src 'self' 'nonce-%s';", nonce))
                .append("script-src-attr 'none';")
                .append("style-src 'self' https: 'unsafe-inline';")
                .append("upgrade-insecure-requests");

        return policyBuilder.toString();
    }
}