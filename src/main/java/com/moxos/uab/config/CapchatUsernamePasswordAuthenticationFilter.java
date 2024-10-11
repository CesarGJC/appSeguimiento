package com.moxos.uab.config;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class CapchatUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String valor = request.getParameter("captcha");
        String captcha = (String) request.getSession().getAttribute("CAPTCHA");

        if (valor == null || !valor.equals(captcha)) {
            this.reCaptchaError(request, response, "ReCaptcha failed");
            return null;
        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    private void reCaptchaError(HttpServletRequest request, HttpServletResponse response, String errorMsg) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login?error=2");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new AuthenticationServiceException("Captcha failed : " + errorMsg);
        } catch (IOException e) {
            throw new AuthenticationServiceException("captcha failed : " + errorMsg);
        }
    }
}