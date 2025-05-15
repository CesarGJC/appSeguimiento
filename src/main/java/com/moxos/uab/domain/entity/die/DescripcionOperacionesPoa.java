package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DescripcionOperacionesPoa extends Entidad {
    private Integer id_descripcion_operaciones_poa;
    private Integer id_accion_corto_plazo_poa;
    private String descripcion_operaciones_poa;
    private String codigo;
    private String denominacion_indicador;
    private String tipo_unidad;
    private String formula;
    private String meta_base_estimada;
    private String meta_base;
    private Integer orden;
    private Integer linea_base;
    private Integer id_resultados;

    private Integer primerTrimestre;
    private Integer segundoTrimestre;
    private Integer tercerTrimestre;
    private Integer cuartoTrimestre;
    private int id_programa;
    private int id_departamento;
    private int id_detalle_periodos_programacion;
}
