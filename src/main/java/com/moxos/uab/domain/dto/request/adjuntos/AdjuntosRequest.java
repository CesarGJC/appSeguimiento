package com.moxos.uab.domain.dto.request.adjuntos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdjuntosRequest {
    private int id_adjunto;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_evaluacion_desempeno;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String nro_documento;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String tipo_documento;
}
