package com.moxos.uab.domain.dto.request.operaciones;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperacionesFilterRequest implements Serializable {
    private String gestion;
    private String plan_pei;
    private String area_estrategica;
    private String codigo;
    private String titulo;

    private Integer pagina;
    private Integer nro_pagina;
}
