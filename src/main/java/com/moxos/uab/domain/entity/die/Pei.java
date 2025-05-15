package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Pei extends Entidad {
    private Integer id_plan_pei;
    private String gestion;
    private String descripcion;
    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
