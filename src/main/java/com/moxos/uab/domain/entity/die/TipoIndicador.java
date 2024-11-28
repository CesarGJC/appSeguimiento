package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class TipoIndicador extends Entidad {
    private Integer id_tipo_indicador;
    private String descripcion;
    private String abreviacion;

    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
