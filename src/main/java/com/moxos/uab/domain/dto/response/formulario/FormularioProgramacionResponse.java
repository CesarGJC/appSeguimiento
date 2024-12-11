package com.moxos.uab.domain.dto.response.formulario;

import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.PeriodosProgramacionResponse;
import lombok.Data;

import java.util.List;

@Data
public class FormularioProgramacionResponse {
    private Integer id_formulario;
    private String gestion;
    private String plan_pei;
    private String area_estrategica;
    private String apertura_programatica;
    private String codigo;
    private String titulo;
    private String descripcion;
    private String eje;
    private String meta;
    private String resultado;
    private String encargado;
    private Integer id_plan_pei;
    private Integer id_area_estrategica;
    private Integer id_apertura_programatica;
    private List<PeriodosProgramacionResponse> periodos;
}
