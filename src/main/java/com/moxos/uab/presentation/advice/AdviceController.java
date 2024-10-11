package com.moxos.uab.presentation.advice;

import com.moxos.uab.common.exception.DieExcepcion;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.imageio.IIOException;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(DieExcepcion.class)
    public String dieexceptionHandler() {
        return "Error/Error";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler() {
        return "Error/Error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String ioHandler() {
        return "Error/Error";
    }
}
