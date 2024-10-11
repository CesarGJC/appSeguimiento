package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Libretas extends Grupos {

    /* Private Fields */
    private int id_fase;
    private String fase;
    private String descripcion;
    private int cantidad;
    private int ponderacion;
    private int id_tipo_nota;
    private String tipo_nota;
    private int id_evaluacion;
    private int id_tipo_grado;
    private int padre;
    private int id_tipo_docente;
    private int id_modelo_fase;
    private double nota;
    private List<Libretas> notas;
    private List notafinalfase;
    private List faseponderaciones;
    private List ponderaciones;
    private List notafinal;
    private int id_docente;
    private int ponderacionmodelofase;
    private float ponderacionmodelofase1;
    private float ponderacion1;
    private float nota_ponderada;
    private String observacion;
    private float nota_semi;
    private String nombres;
    private String nombre;
    private int nro_nota;
    private double nota_aprobacion;
    private int id_persona;
    private Integer porcentaje;

    private String cod_l;
    private String cod_n;
    private String nota_literal;
    private int id_nota;
    //private List lbr_tipos_notas;
    private int id_prg_grado_academico;
    private int id_grado_academico;
    private String grado_academico;
    private int id_lbr_tipo_nota;
    private boolean habilitado;
    private int id_lbr_fase;
    //private List materias;
    private int nro_asignaciones;
    private int id_libreta;
    private int id_matricula;
    private String literal;
    private int numero;
    private boolean bandera;
    private double nota_final;
    private double nota_final_ponderada;
    private String sigla;
    private int nivel_academico;
    private String materia;
    private String fec_actual;
    private int nro_tipo_nota;
    private String fecha_inicio;
    private String fecha_limite;
    private String paterno;
    private String materno;
    private String dip;
    private String id_plan;
    private String grupo;
    private int permitidomodificar;
    private String ip;
    private String ubicacion;
    private String detalle_dispositivo;
    private String sede_desconcentrada;
    private int id_estudiante;
    private String tipo_evaluacion;
}
