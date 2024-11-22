package com.moxos.uab.domain.dto.response.areasestrategicas;

import lombok.Data;

@Data
public class AreaEstrategicaResponse {
    private int id_area_estrategica;
    private String area_estrategica;
    private int id_plan_pei;
    private String gestion;
    private String descripcion;
}
