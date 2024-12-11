package com.moxos.uab.domain.dto.response.aperturasprogramaticas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AperturasProgramaticasRequest {
    private Integer id_apertura_programatica;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String apertura_programatica;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String codigo;
    private Integer ult_usuario;
}
