package com.moxos.uab.domain.entity.die;

import lombok.Data;

@Data
public class Trimestre {
    private Integer id_trimestre;
    private String trimestre;
    private Integer programado;
    private Integer id_descripcion_operaciones_poa;
    private double ejecutado;
}
