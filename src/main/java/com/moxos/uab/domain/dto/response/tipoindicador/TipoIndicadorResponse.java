package com.moxos.uab.domain.dto.response.tipoindicador;

import lombok.Data;

@Data
public class TipoIndicadorResponse {
    private int id_tipo_indicador;
    private String descripcion;
    private String abreviacion;
}
