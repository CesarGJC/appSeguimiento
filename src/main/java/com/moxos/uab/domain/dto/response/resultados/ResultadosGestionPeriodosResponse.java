package com.moxos.uab.domain.dto.response.resultados;

import lombok.Data;

@Data
public class ResultadosGestionPeriodosResponse {
    private int id_resultados;
    private int id_detalle_periodos_programacion;
    private String descripcionResultado;
}
