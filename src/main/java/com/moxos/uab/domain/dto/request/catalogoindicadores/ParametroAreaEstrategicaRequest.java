package com.moxos.uab.domain.dto.request.catalogoindicadores;

import lombok.Data;

@Data
public class ParametroAreaEstrategicaRequest {
    private Integer id;
    private Integer opcion;

    public ParametroAreaEstrategicaRequest(Integer id, Integer opcion) {
        this.id = id;
        this.opcion = opcion;
    }
}
