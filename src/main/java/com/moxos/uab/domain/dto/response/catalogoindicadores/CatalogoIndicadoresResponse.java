package com.moxos.uab.domain.dto.response.catalogoindicadores;

import lombok.Data;

@Data
public class CatalogoIndicadoresResponse {
    private int id_catalogo_indicador;
    private int id_indicador_estrategico;
    private String catalogo_indicador;
    private String meta;
    private String linea_base;
    private String denominacion_indicador;
    private String formula;
    private String medios_verificacion;
    private int ult_usuario;
}
