package com.moxos.uab.domain.dto.request.configuracion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfiguracionRequest {
    private String clave;
    private String valor;
}
