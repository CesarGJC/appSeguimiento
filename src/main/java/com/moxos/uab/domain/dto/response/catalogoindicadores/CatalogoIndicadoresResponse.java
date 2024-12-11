package com.moxos.uab.domain.dto.response.catalogoindicadores;

import lombok.Data;

@Data
public class CatalogoIndicadoresResponse {
    private int id_catalogo_indicador_pei;
    private String area_estrategica;
    private String denominacion_indicador;
    private String tipo_indicador;
    private String unidad;
    private String categoria;
    private String articulacion;
}
