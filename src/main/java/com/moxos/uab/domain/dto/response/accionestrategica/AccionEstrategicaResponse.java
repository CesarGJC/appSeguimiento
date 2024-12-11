package com.moxos.uab.domain.dto.response.accionestrategica;

import lombok.Data;

@Data
public class AccionEstrategicaResponse {
    private int id_acciones_estrategica;
    private int id_objetivo_estrategica;
    private String objetivo_estrategica;
    private int orden;
}
