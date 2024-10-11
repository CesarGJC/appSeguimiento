package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Grupos extends Modelos_ahorros {

    /* Private Fields */
    private int id_dpto_grupo;
    private int cupo_actual;
    private int cupo_max;
    private double horas;
    private int nro_resolucion;
    private Date fec_resolucion;
    private int id_grupo_ant;
    private int id_postulante;
    private int resultado;
    private int id_asignacion;
    private String fec_resolucion2;
    private String snro_resolucion;
    private int id_fase_resolucion;

}
