package com.moxos.uab.presentation.advice;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class ErrorController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle404(HttpServletRequest request, NoHandlerFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error-404");
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }

    @RequestMapping("/error")
    public String handleError() {
        return "Advice/error";
    }
}