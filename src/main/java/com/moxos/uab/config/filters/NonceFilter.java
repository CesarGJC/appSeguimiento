package com.moxos.uab.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

public class NonceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String nonce = UUID.randomUUID().toString(); // Generar un nonce único

        // Añadir el nonce como atributo de la solicitud
        httpRequest.setAttribute("nonce", nonce);

        // Pasar la solicitud y la respuesta al siguiente filtro
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización, si es necesaria
    }

    @Override
    public void destroy() {
        // Limpieza de recursos, si es necesaria
    }
}
