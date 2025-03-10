package com.moxos.uab.domain.dto.response.resultados;

import lombok.Data;

@Data
public class ResultadosDetalleResponse {
    private Integer id_resultados;
    private String acciones_estrategica;
    private String descripcion;
    private String formula;
    private String linea_base;
    private String meta_base;
    private String fuente_informacion;
    private String denominacion_indicador;
    private String categoria_indicador;
    private String codigo;
    private String tipo_indicador;
    private String unidad_medidad;
    private String meta;
    private String gestion;
    private String abreviacion;
}
