package com.moxos.uab.domain.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class GeneralResponse {
    private boolean success;
    private String message;
    public GeneralResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
