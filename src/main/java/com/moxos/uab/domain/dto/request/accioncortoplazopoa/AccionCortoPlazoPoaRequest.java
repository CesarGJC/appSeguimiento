package com.moxos.uab.domain.dto.request.accioncortoplazopoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccionCortoPlazoPoaRequest {
    private Integer id_accion_corto_plazo_poa = 0;
    @NotNull(message = "Los Objetivos de gestion no pueden estar vacio.")
    private Integer id_objetivos_gestion_poa;
    @NotNull(message = "La Accion a corto plazo no puede estar vacio.")
    @NotBlank(message = "La Accion a corto plazo es obligatorio.")
    private String accion_corto_plazo_poa;
    private Integer id_detalle_periodos_programacion;
    private Integer id_formulario;
    private Integer id_programa;
    private Integer id_departamento;
    private Integer orden;
    private Integer ult_usuario;
}
