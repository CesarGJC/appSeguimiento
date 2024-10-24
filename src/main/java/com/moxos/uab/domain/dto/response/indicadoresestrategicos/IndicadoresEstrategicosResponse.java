package com.moxos.uab.domain.dto.response.indicadoresestrategicos;

import lombok.Data;

@Data
public class IndicadoresEstrategicosResponse {
    private int id_indicador_estrategico;
    private int id_politica_desarrollo;
    private String indicador_estrategico;
    private String politica_desarrollo;
    private String area_estrategica;
}
