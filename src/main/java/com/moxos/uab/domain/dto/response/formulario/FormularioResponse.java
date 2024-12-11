package com.moxos.uab.domain.dto.response.formulario;

import lombok.Data;

@Data
public class FormularioResponse {
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
}
