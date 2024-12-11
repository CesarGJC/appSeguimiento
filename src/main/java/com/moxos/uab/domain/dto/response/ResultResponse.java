package com.moxos.uab.domain.dto.response;

import lombok.Data;

@Data
public class ResultResponse {
    private String message;
    private String className;

    public ResultResponse(String message, String className) {
        this.message = message;
        this.className = className;
    }
}
