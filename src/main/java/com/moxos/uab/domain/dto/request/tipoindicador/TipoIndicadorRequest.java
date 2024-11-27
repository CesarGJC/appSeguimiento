package com.moxos.uab.domain.dto.request.tipoindicador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoIndicadorRequest {
    private int id_tipo_indicador;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String descripcion;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String abreviacion;
    private Integer ult_usuario;
}
