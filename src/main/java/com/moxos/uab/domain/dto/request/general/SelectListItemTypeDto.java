package com.moxos.uab.domain.dto.request.general;

import lombok.Getter;
import lombok.Setter;

public class SelectListItemTypeDto extends SelectListItemDto {
    @Getter
    @Setter
    private Integer type;

    public SelectListItemTypeDto(String id, String value, Integer type) {
        super(id, value);
        this.type = type;
    }
}
