package com.moxos.uab.domain.dto.request.indicadoresestrategicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IndicadoresEstrategicosRequest {
    private int id_indicador_estrategico;
    private int id_politica_desarrollo;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String indicador_estrategico;
    private int ult_usuario;
}
