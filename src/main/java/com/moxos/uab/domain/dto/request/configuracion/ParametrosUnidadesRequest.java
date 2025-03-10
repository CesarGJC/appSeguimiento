package com.moxos.uab.domain.dto.request.configuracion;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParametrosUnidadesRequest {
    @NotNull(message = "Debe establecer el id del Plan Estrategico Institucional")
    private Integer id;
    @NotNull(message = "Debe establecer el tipo de unidad")
    private Integer tipoUnidad = 1;
}
