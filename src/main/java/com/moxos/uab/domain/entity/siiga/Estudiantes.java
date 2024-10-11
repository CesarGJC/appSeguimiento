package com.moxos.uab.domain.entity.siiga;

import java.util.Date;
import java.lang.String;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Estudiantes extends Personas {

    /* Private Fields */
    private int id_estudiante;
    private int id_programa;
    private String id_plan;
    private String fec_inicio;
    private String fec_fin;
    private int id_grado;
    private int id_tipo_grado;
    private String tipo_grado;
    private int id_tipo_estudiante;
    private String programa;
    private int id_tipo_admision;
    private Date fec_inscripcion;
    private Date fec_egreso;
    private int id_matricula;
    private String rol;
    private int id_tipo_documento;
    private String tipo_documento;
    private int id_tipo_clasificacion;
    private String tipo_clasificacion;
    private int id_grupo;
    private String grupo;
    private int id_materia;
    private String materia;
    private String sigla;
    private boolean vigente;
    private int id_tipo_graduacion;
    private String tipo_graduacion;
    private String tipo_admision;
    private int id_facultad;
    private String facultad;
    private int id_est_adjunto;
    private String nombre_archivo;
    private String adjunto;
    private int cantidad;
    private int aprobados;
    private int reprobados;
    private int abandonos;
    private String tipo_sexo;
    private String nacionalidad;
    private String tipo_estudiante;
    private String departamento;
    private int plan;
    private int notaa;
    private int notar;
    private int notaab;
    private int id_regularizacion;
    private int id_tipo_regularizacion;
    private String tipo_regularizacion;
    private int id_perfil;
    private double costo;
    private int id_tipo_evaluacion;
    private int id_modelo_ahorro;
    private int id_programacion;
    private String tipo_evaluacion;
    private String periodogestion;
    private int nivel_academico;
    private int id_programa_ant;
    private String fecha_ini;
    private String fecha_fin;
    private int id_est_clasificacion;
    private int id_tipo_descuento;
    private int id_clf_tipo_documento;
    private int id_est_deuda;
    private int id_tipo_deuda;
    private String tipo_deuda;
    private boolean regularizado;
    private boolean cancelado;
    private float promedio;
    private String modalidad_beca;
    private Date fecha_i;
    private Date fecha_f;
    private String carga_horaria;
    private int id_ubicacion_organica;
    private int cant_materias;
    // private List estudiantes;
    private int ins_sede;
    private int id_tipo_materia;
    private String sede_desconcentrada;

    // Aumentado d
    private Date fec_ingreso;
    private int id_persona;
    private String nombres;
    private String transaccion;
    // Aumentado d

    // Aumentado CODE
    private int id_sede;
    private int id_concepto;
    private String sede;
    private int nro_certificado;
    private int gestion_certificado;
    private String nrocertificado_gestion;
    private String nro_transaccion;
    private int id_notas_certificados;
    // private String facultad;
    private String carrera;
    private String nivel;
    private int nivel2;
    private String planes;
    private String periodo_academico;
    private int ru;
    private int ci;
    private String estudiante;
    private String obs;
    private int reimpresiones;
    // Aumentado notas CODE
    public int id_certificados_generados;
    public String nro_certificado2;
    // public String nro_certificado_generado;
    // public String sigla;
    // public String nivel;
    public String asignatura;
    public double numeral;
    public String literal;
    // public String tipo_evaluacion;
    public String observacion;
    public String id_estado;

}
