package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UnidadMedida extends Entidad {
    private Integer id_unidad_medida;
    private String descripcion;
    private String abreviacion;

    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
