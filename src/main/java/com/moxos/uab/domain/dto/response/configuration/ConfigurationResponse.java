package com.moxos.uab.domain.dto.response.configuration;

import lombok.Data;

@Data
public class ConfigurationResponse {
    private int id_configuracion;
    private String gestion;
    private String clave;
    private String valor;
    private String descripcion;
    private String etiqueta;
    private int tipo_valor;

}
