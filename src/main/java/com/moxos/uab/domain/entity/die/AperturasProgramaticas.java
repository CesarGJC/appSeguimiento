package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AperturasProgramaticas extends Entidad {
    private Integer id_apertura_programatica;
    private String apertura_programatica;
    private String codigo;

    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
