package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormularioProgramacion extends Entidad {
    private Integer id_formulario;
    private Integer id_area_estrategica;
    private Integer id_apertura_programatica;
    private String titulo;
    private String descripcion;
    private String eje;
    private String meta;
    private String resultado;
    private String encargado;

    private Integer id_detalle_periodos_programacion;
    private Integer id_plan_pei;
    private String gestion;
    private String plan_pei;
    private String area_estrategica;
    private String apertura_programatica;
    private String codigo;
    private String gestion_periodo;
}
