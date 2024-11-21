package com.moxos.uab.domain.dto.request.areasestrategicas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AreasEstrategicasRequest {
    private int id_area_estrategica = 0;
    @NotNull(message = "Debe la descripcion del area estrategica")
    @NotBlank(message = "Debe la descripcion del area estrategica")
    private String area_estrategica;
    @NotNull(message = "Debe ingresar la gestion del area estrategica segun el PEI")
    @NotBlank(message = "Debe ingresar la gestion del area estrategica segun el PEI")
    private String gestion;
    @NotNull(message = "Debe ingresar el Codigo del area estrategica segun el PEI")
    @NotBlank(message = "Debe ingresar el Codigo del area estrategica segun el PEI")
    private String codigo;
    private Integer ult_usuario;
}
