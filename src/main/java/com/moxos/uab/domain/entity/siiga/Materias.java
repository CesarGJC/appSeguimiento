package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Materias extends Departamentos {

    /* Private Fields */
    private int id_materia;

    private int id_tipo_materia;
    private String materia;
    private String sigla;
    private int hrs_teoricas;
    private int hrs_practicas;
    private int hrs_periodo;
    private float creditos;
    //private List grupos;
    private int id_grupo;
    private String tipo_materia;
    private int cupo_restante;
    private int nro_grupos;
    private int nivel_academico;
    //EST_PROGRAMACIONES
    private int id_modelo_ahorro;
    private String modelo_ahorro;
    private String grupo;
    private int id_estudiante;
    private int id_mencion;
    private int id_programa;
    private int id_postulante;
    private int max_niveles;
    //estadisticas
    private String programa;
    private int id_tipo_grado;
    private String id_plan;
    private int id_tipo_evaluacion;
    //private List asignaciones;
    private int nro_asignaciones;
    //private List materias;
    private String estado;
    private String tipo_grado;
    private boolean es_electiva;
    private String mencion;

    private String sigla_origen;
    private String materia_origen;
    private int similitud;
    private int nota_origen;

}
