package com.moxos.uab.domain.dto.response.adjuntos;

import lombok.Data;

@Data
public class AdjuntosResponse {
    private int id_adjunto;
    private int id_evaluacion_desempeno;
    private String nro_documento;
    private String tipo_documento;
}
