package com.moxos.uab.domain.dto.request.detalleperiodoprogramacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetallePeriodoProgramacionRequest {
    private Integer id_detalle_periodos_programacion = 0;
    private Integer id_plan_pei;
    private String plan_pei;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String descripcion;
    private Integer ult_usuario;
}
