package com.moxos.uab.domain.dto.response.objetivoestrategia;

import lombok.Data;

@Data
public class ObjetivoEstrategiaResponse {
    private int id_objetivo_estrategica;
    private int id_area_estrategica;
    private String area_estrategica;
    private String objetivo_estrategica;
    private int orden;
}
