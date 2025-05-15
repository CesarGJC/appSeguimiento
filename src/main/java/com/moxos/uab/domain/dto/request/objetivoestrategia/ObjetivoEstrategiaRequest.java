package com.moxos.uab.domain.dto.request.objetivoestrategia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ObjetivoEstrategiaRequest {
    private int id_objetivo_estrategica;
    @NotNull(message = "Debe seleccionar la Area Estrategica")
    private int id_area_estrategica;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String objetivo_estrategica;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private int orden;
}
