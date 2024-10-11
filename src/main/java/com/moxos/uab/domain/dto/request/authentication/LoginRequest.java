package com.moxos.uab.domain.dto.request.authentication;

import lombok.Data;

@Data
public class LoginRequest {
    private String apodo;
    private String clave;
    private String captcha;
    private String message;
    private Integer error;
}

