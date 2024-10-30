package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class CatalogoIndicadores extends Entidad {
    private int id_catalogo_indicador;
    private int id_indicador_estrategico;
    private String indicador_estrategico;
    private String catalogo_indicador;
    private String meta;
    private String linea_base;
    private String denominacion_indicador;
    private String formula;
    private String medios_verificacion;

    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
