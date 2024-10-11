package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asignaciones extends Grupos {

    /* Private Fields */
    private int id_asignacion;
    private String busqueda;
    private int id_docente;
    private int id_modelo_ahorro;
    private String modelo_ahorro;
    private int id_programa;
    private int id_materia;
    private String materia;
    private String sigla;
    private int nivel_academico;
    private int id_grupo;
    private String grupo;
    private int id_fase;
    private String fase;
    private String programa;
    private double nota_aprobacion;
    private int ponderacion_modelo;
    private int ponderacion_materia;
    private int hrs_teoricas;
    private int hrs_practicas;
    // private float creditos;
    private int id_tipo_materia;
    private int id_departamento;
    private String descripcion;
    private int cantidad;
    private int ponderacion;
    private int id_tipo_nota;
    private String tipo_nota;
    private int gestion;
    private int periodo;
    private String nombres;
    private Date fec_registro;
    private int ult_usuario;
    private int id_modelo_fase;
    private int padre;
    private int id_evaluacion;
    // private List materia_ahorro;
    private int id_tipo_grado;
    private String tipo_grado;
    private int id_tipo_docente;
    private String tipo_docente;
    private int id_tipo_asignacion;
    private String tipo_asignacion;
    private int id_tipo_evaluacion;
    private String tipo_evaluacion;
    private String observacion;
    private String fec_inicio;
    private String fec_fin;
    // private String fec_resolucion;
    private int resolucion;
    private String dip;
    private int id_mencion;
    private String mencion;
    private String paterno;
    private String materno;
    private int id_persona;
    private Date fec_inicio2;
    private Date fec_fin2;
    // private List materias;
    private int total_horas;
    private int count;
    private String carga_horaria;
    private String id_plan;
    private Integer permitir;
    private Integer id_dct_programa_analitico;

    private String nro_resolucionhcc;
    private String nro_resolucionhcf;
    private String nro_resolucionhcu;
    private int id_fase_resolucion;
    private int nro_memo;
    private Date fecha;
    private String nombrecompleto;
    private String nombre_completo;
    private int nro_designacion;
    private int id_periodo;

}
