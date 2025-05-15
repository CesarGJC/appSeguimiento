package com.moxos.uab.domain.dto.response.descripcionoperacionespoa;

import com.moxos.uab.domain.dto.response.trimestre.TrimestreResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DescripcionOperacionesPoaResponse {
    private int id_descripcion_operaciones_poa;
    private int id_accion_corto_plazo_poa;
    private String descripcion_operaciones_poa;
    private String accion_corto_plazo_poa;
    private int id_programa;
    private int id_departamento;
    private String codigo;
    private String denominacion_indicador;
    private String tipo_unidad;
    private String formula;
    private String meta_base_estimada;
    private String meta_base;
    private int id_detalle_periodos_programacion;
    private int id_resultados;
    private int orden;
    private int linea_base;
    private int cantidad;
    private String resultado;
    private Integer primerTrimestre;
    private Integer segundoTrimestre;
    private Integer tercerTrimestre;
    private Integer cuartoTrimestre;
    private List<TrimestreResponse> detalleTrimestre;
    private List<ListView> resultadosEsperados;

}

