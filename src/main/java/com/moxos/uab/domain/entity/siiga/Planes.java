package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planes extends Menciones {

    /* Private Fields */
    private String id_plan_ant;
    private int id_tipo_evaluacion;
    private int id_materia_ant;
    private int id_materia;
    private String materia;
    private int nivel_academico;
    private int id_tipo_materia;
    private int id_grado;
    private int id_tipo_grado;
    private String tipo_grado;
    private String tipo_materia;
    private String grado_academico;
    private int id_grado_academico;
    private String tipo_evaluacion;
    private int id_programa;
    private int numero;
    private int aprobados;
    private int reprobados;
    private int abandonos;
    private String plan;
    private String detalles;
    private String resolucion;
    private boolean actual;
    private String materias_anteriores;
    private int id_mtr_plan;
    private int id_perfil;
    private double costo;
    private int id_prg_plan;
    private int hrs_teoricas;
    private int hrs_practicas;
    private int creditos;
    private int id_tipo_sexo;

    private int id_tipo_convalidacion;
    private String tipo_convalidacion;
    private String sigla_origen;
    private String materia_origen;
    private int similitud;
    private int nota_origen;
    private int id_convalidacion;
    private String nro_resolucion;
    private String paterno;
    private String materno;
    private String nombres;
    private int id_estudiante;
    private int id_cnv_detalle;
    private int id_nota;
    private int id_matricula;

}