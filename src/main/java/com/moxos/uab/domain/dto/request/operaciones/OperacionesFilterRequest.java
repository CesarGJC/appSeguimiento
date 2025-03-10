package com.moxos.uab.domain.dto.request.operaciones;

import lombok.Data;

@Data
public class OperacionesFilterRequest {
    private String operaciones;
    private String gestion;
    private String elaborador;
    private String resultado;
    private String indicador;
    private String titulo;

    private Integer pagina;
    private Integer nro_pagina;
}
