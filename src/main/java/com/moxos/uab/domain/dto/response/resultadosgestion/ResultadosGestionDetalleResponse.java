package com.moxos.uab.domain.dto.response.resultadosgestion;

import lombok.Data;

@Data
public class ResultadosGestionDetalleResponse {
    private Integer id_resultados_gestion;
    private Integer id_resultados = 0;
    private String descripcion;
    private String descripcionPeriodo;
    private Integer id_detalle_periodos_programacion;
}
