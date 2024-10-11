package com.moxos.uab.domain.dto.request.politicasdesarrollo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PoliticasDesarrolloRequest {
    private int id_politica_desarrollo;
    private int id_area_estrategica;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String politica_desarrollo;
    private int ult_usuario;
}
