package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Modelos_ahorros extends Materias {

    /* Private Fields */
    private int ponderacion_modelo;
    private int ponderacion_materia;
    private double nota_aprobacion;
    private String pago;
    private String id_plan;
    private int id_programa;
    private int id_fase_resolucion;

}
