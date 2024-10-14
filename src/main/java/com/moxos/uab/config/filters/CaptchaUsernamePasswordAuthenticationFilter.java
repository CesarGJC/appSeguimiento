package com.moxos.uab.config.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CaptchaUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String captchaParameter = "captcha"; // El parámetro que contiene el CAPTCHA

    public CaptchaUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // Obtener nombre de usuario y contraseña de la solicitud
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // Obtener el CAPTCHA de la solicitud
        String captcha = request.getParameter(captchaParameter);

        // Validar CAPTCHA (puedes implementar tu lógica de validación aquí)
        if (!isCaptchaValid(captcha, request)) {
            throw new AuthenticationException("Captcha inválido") {
            };
        }

        // Crear el token de autenticación
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Devolver el token para que sea autenticado
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    // Lógica de validación del CAPTCHA
    private boolean isCaptchaValid(String captcha, HttpServletRequest request) {
        String value = (String) request.getSession().getAttribute("CAPTCHA");

        // Implementa aquí la validación de tu CAPTCHA (comparar con algún valor generado previamente, etc.)
        // Esta es solo una verificación de ejemplo.
        return captcha.equals(value); // ¡Solo un ejemplo simple! En la vida real, debes usar un servicio CAPTCHA real.
    }
}