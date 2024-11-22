package com.moxos.uab.domain.entity.die;

import lombok.Data;

@Data
public class DetallePeriodoProgramacion {
    private Integer id_detalle_periodos_programacion;
    private Integer id_plan_pei;
    private String descripcion;
    private String descipcion_plan;

    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;


}
