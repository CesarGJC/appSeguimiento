package com.moxos.uab.presentation.advice;

import com.moxos.uab.common.exception.DieExcepcion;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.imageio.IIOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class AdviceController {
    @ModelAttribute
    public void getCsrfToken(HttpServletResponse response, CsrfToken csrfToken) {
        response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model modelo, Exception e) {
        modelo.addAttribute("mensaje", e.getMessage());
        return "Advice/Error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "Advice/AccessDenied";
    }
}
