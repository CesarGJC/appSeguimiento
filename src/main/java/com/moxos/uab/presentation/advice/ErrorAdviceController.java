package com.moxos.uab.presentation.advice;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorAdviceController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model modelo) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                modelo.addAttribute("mensaje", "No se encuentra el archivo");
                return "Advice/Error"; // Página personalizada para 404
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "Advice/error"; // Página personalizada para 500
            }
        }
        return "Advice/error"; // Página de error genérica
    }
}