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
    private Integer id_plan_pei;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String gestion;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String descripcion;
    private Integer ult_usuario;
}
