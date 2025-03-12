package com.moxos.uab.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HtmlMinifyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (!(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper);

        // Verificar si la respuesta es HTML
        String contentType = responseWrapper.getContentType();
        if (contentType != null && contentType.contains("text/html")) {
            // Minificar HTML antes de enviarlo
            String originalContent = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
            String minifiedHtml = minifyHtml(originalContent);

            response.getWriter().write(minifiedHtml);
        } else {
            // Si no es HTML, devolver la respuesta original
            responseWrapper.copyBodyToResponse();
        }
    }
    @Override
    public void destroy() {
    }
    private String minifyHtml(String html) {
        return html.replaceAll("\\s{2,}", " ") // Reemplaza múltiples espacios
                .replaceAll(">\\s+<", "><") // Elimina espacios entre etiquetas
                .trim();
    }
}