package com.moxos.uab.domain.dto.request.general;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilterParamsRequest  implements Serializable {
    private String option;
    private Integer type;
    private Object value;
}
