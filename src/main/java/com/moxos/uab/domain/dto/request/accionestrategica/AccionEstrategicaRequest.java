package com.moxos.uab.domain.dto.request.accionestrategica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccionEstrategicaRequest {
    private int id_acciones_estrategica;
    @NotNull(message = "Debe seleccionar la Area Estrategica")
    private int id_objetivo_estrategica;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String objetivo_estrategica;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private int orden;
}
