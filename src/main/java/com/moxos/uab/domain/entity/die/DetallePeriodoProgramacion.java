package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class DetallePeriodoProgramacion extends Entidad {
    private Integer id_detalle_periodos_programacion;
    private Integer id_plan_pei;
    private String descripcion;
    private String plan_pei;

    private Integer pagina;
    private Integer nro_pagina;
    private String buscar;

}
