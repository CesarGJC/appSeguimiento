package com.moxos.uab.domain.dto.request.pei;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PeiRequest {
    private Integer id_plan_pei;
    @NotNull(message = "La Gestion no puede estar vacio.")
    @NotBlank(message = "La Gestion es obligatorio.")
    private String gestion;
    @NotNull(message = "El Plan Pei no puede estar vacio.")
    @NotBlank(message = "El Plan Pei es obligatorio.")
    private String descripcion;
    private Integer ult_usuario;
}
