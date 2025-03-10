package com.moxos.uab.domain.dto.response.aperturasprogramaticas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AperturasProgramaticasRequest {
    private Integer id_apertura_programatica;
    @NotNull(message = "Debe ingresar el nombre del Formulario")
    @NotBlank(message = "Debe ingresar el nombre del Formulario")
    private String apertura_programatica;
    @NotNull(message = "Debe ingresar el Código del Formulario")
    @NotBlank(message = "Debe ingresar el Código del Formulario")
    private String codigo;
    private Integer ult_usuario;
}
