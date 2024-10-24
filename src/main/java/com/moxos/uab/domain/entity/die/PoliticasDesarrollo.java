package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

import java.util.Date;

@Data
public class PoliticasDesarrollo extends Entidad {
    private int id_politica_desarrollo;
    private int id_area_estrategica;
    private String politica_desarrollo;

    private String area_estrategica;
    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
