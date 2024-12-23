package com.moxos.uab.domain.dto.response.resultados;

import lombok.Data;

@Data
public class ResultadosResponse {
    private int id_resultados;
    private int id_acciones_estrategica ;
    private String descripcion ;
    private String formula ;
    private String linea_base ;
    private String meta_base ;
    private String fuente_informacion ;
    private String denominacion_indicador ;
    private int id_unidad_medida ;
    private int id_tipo_indicador ;
    private int id_categoria ;
    private String articulacion  ;
}
