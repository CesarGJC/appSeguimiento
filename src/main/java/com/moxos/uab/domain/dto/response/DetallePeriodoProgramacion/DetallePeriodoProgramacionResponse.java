package com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion;

import lombok.Data;

@Data
public class DetallePeriodoProgramacionResponse {
    private Integer id_detalle_periodos_programacion;
    private Integer id_plan_pei;
    private String descripcion;
    private String plan_pei;


    public DetallePeriodoProgramacionResponse(Integer id_detalle_periodos_programacion, String descripcion ) {
        this.id_detalle_periodos_programacion = id_detalle_periodos_programacion;
        this.descripcion = descripcion;
    }
    public DetallePeriodoProgramacionResponse() {}
}
