package com.moxos.uab.domain.dto.request.configuracion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfiguracionRequest {
    private String clave;
    @NotNull(message = "Debe seleccionar el Valor")
    @NotBlank(message = "Debe seleccionar el Valor")
    private String valor;
}
