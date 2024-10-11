package com.moxos.uab.domain.dto.response.politicasdesarrollo;

import lombok.Data;

@Data
public class PoliticasDesarrolloResponse {
    private int id_politica_desarrollo;
    private int id_area_estrategica;
    private String politica_desarrollo;
    private int ult_usuario;
}
