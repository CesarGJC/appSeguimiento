package com.moxos.uab.domain.dto.request.objetivoestrategia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ObjetivoEstrategiaRequest {
    private int id_objetivo_estrategica;
    @NotNull(message = "Debe seleccionar la Area Estrategica")
    private int id_area_estrategica;
    @NotNull(message = "El Objetivo Estrategico no puede estar vacio.")
    @NotBlank(message = "El Objetivo Estrategico es obligatorio.")
    private String objetivo_estrategica;
    @NotNull(message = "El Orden no puede estar vacio.")
    @NotBlank(message = "El Orden es obligatorio.")
    private int orden;
}
