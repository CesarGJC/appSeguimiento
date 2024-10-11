package com.moxos.uab.domain.dto.response;

import lombok.Data;

@Data
public class Response<T> {
    private boolean success;
    private String message;
    private T result;

    public Response(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }
}
