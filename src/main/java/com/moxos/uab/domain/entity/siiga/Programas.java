package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Programas extends Facultades {

    /* Private Fields */
    private int id_programa;
    private int id_campus;
    private int id_area_conocimiento;
    private int id_grado_academico;
    private int id_tipo_ensenyanza;
    private int id_tipo_graduacion;
    private int id_tipo_admision;
    private int id_periodo;
    private int id_sede;
    private String programa;
    private String resolucion_hcu;
    private Date fec_inicio;
    private String mision;
    private String objetivos;
    private int duracion;
    private String turno;
    private int nro_estudiante;
    private float nota_aprobacion;
    private int nota_minima;

    private float nota_admision;
    // EST_PROGRAMACIONES
    private int max_materias_laboratorios;
    private int max_materias_teoricas;
    private float costo_materia_laboratorio;
    private float costo_materia_teorica;
    private String id_plan;
    private int id_tipo_programacion;
    private int max_niveles;
    private int id_estudiante;
    private String modelo_ahorro;
    private String materia;
    private String grupo;
    private int id_grupo;
    private int id_materia;
    private int id_modelo_ahorro;
    private int id_postulante;
    private Date fec_final;
    private String tipo_programacion;

    private int id_mencion;
    private String mencion;
    private int nivel_academico;
    private String materias;
    private int id_detalle;
    private String nombres;
    private int id_programacion;
    private int id_rol;
    private String tipo_admision;
    private int gestion;
    private int periodo;

    // estadisticas
    private int id_tipo_grado;
    private String tipo_grado;
    private String plan;
    private int id_tipo_evaluacion;
    private String tipo_evaluacion;

}
