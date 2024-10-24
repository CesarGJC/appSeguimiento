package com.moxos.uab.domain.dto.request.general;

import lombok.Data;

@Data
public class SelectListItemDto {
    private String id;
    private String value;
    private boolean selected;

    public SelectListItemDto(String id, String value) {
        this.id = id;
        this.value = value;
    }
}
