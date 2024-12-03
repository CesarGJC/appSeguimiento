package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class CatalogoIndicadores extends Entidad {
    private Integer id_catalogo_indicador_pei;
    private Integer id_area_estrategica;
    private String denominacion_indicador;
    private Integer id_unidad_medida;
    private Integer id_tipo_indicador;
    private Integer id_categoria;
    private String articulacion;

    private String area_estrategica;
    private String tipo_indicador;
    private String unidad;
    private String categoria;
    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;
}
