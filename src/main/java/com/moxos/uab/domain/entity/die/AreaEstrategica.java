package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class AreaEstrategica extends Entidad {
    private int id_area_estrategica;
    private String area_estrategica;
    private String id_estado;
    private Date fec_registro;
    private Date fec_modificacion;
    private int ult_usuario;
    private Integer id_plan_pei;
    private Integer orden;

    private Integer pagina;
    private Integer nro_pagina;
    private String descripcion;
    private String buscar;
}
