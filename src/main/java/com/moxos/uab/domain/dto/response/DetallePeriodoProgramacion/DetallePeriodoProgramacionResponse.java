package com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion;

import lombok.Data;

@Data
public class DetallePeriodoProgramacionResponse {
    private Integer id_detalle_periodos_programacion;
    private Integer id_plan_pei;
    private String descripcion;
    private String descripcion_plan;
}
