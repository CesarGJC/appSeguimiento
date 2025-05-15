package com.moxos.uab.domain.dto.request.detallePeriodoProgramacion;

import lombok.Data;

@Data
public class ParametroPeiRequest {
    private Integer id;
    private Integer opcion;
    public ParametroPeiRequest(Integer id, Integer opcion) {
        this.id = id;
        this.opcion = opcion;
    }
}
