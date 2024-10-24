package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IndicadoresEstrategicos extends Entidad {
    private int id_indicador_estrategico;
    private int id_politica_desarrollo;
    private String indicador_estrategico;

    private String area_estrategica;
    private String politica_desarrollo;
    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
