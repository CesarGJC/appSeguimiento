package com.moxos.uab.domain.entity.siiga;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParametroBusqueda {
    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
    private Integer sede;
    private String id_estado;
}
