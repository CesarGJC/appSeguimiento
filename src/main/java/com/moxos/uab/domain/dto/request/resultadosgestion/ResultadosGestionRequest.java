package com.moxos.uab.domain.dto.request.resultadosgestion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResultadosGestionRequest {
    private int id_resultados_gestion;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_resultados;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_detalle_periodos_programacion;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String descripcion;
}
