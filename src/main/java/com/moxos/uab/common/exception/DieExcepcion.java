package com.moxos.uab.common.exception;

import org.springframework.http.HttpStatusCode;

import java.io.Serial;

public class DieExcepcion extends Exception {

    public DieExcepcion(String message) {
        super(message);
    }
}
