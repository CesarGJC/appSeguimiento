package com.moxos.uab.domain.dto.response.resultadosgestion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultadosGestionResponse {
    private int id_resultados_gestion;
    private int id_resultados;
    private int id_detalle_periodos_programacion;
    private String descripcion;

    private String resultados;
    private String detalle_periodos_programacion;
    private String acciones_estrategicas;
    private String unidad_medida;
    private String tipo_indicador;
    private String categoria;

    public ResultadosGestionResponse(int id_resultados_gestion, String descripcion) {
        this.id_resultados_gestion = id_resultados_gestion;
        this.descripcion = descripcion;
    }
}
