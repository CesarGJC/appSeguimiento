package com.moxos.uab.domain.dto.request.areasestrategicas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AreasEstrategicasRequest {
    private int id_area_estrategica;
    @NotNull(message = "Debe la descripcion del area estrategica")
    @NotBlank(message = "Debe la descripcion del area estrategica")
    private String area_estrategica;
    private int ult_usuario;
}
