package com.moxos.uab.domain.dto.response.adjuntos;

import lombok.Data;

@Data
public class AdjuntosResponse {
    private Integer id_adjunto_operaciones_actividad;
    private String ruta;
    private String documento;
    private String tipo_documento;
    private String orden;
    private String descripcion_documento;
    private String nombre_documento;
    private String ruta_documento;
}
