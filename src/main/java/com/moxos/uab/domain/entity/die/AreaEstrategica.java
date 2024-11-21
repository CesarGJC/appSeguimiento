package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

import java.util.Date;

@Data
public class AreaEstrategica extends Entidad {
    private int id_area_estrategica;
    private String area_estrategica;
    private Integer pagina;
    private Integer nro_pagina;
    private Date fec_registro;
    private Date fec_modificacion;
    private int ult_usuario;
    private String id_estado;
    private String gestion;
    private String codigo;
    private String buscar;
}
