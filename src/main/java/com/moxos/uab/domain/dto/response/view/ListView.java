package com.moxos.uab.domain.dto.response.view;

import lombok.Data;

@Data
public class ListView {
    private String id;
    private String value;

    public ListView(String id, String value) {
        this.id = id;
        this.value = value;
    }
}
