/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramaAnalitico {

    private int id_persona;
    private String dip;
    private String nombres;
    private String paterno;
    private String materno;
    private String celular;
    private String correo;
    private int id_docente;
    private String categoria;
    private String facultad;
    private String departamento;
    private int gestion;
    private int periodo;
    private String area;
    private String tipo_materia;
    private int id_materia;
    private String sigla;
    private String clasificacion_asignatura;
    private String materia;
    private int hrs_teoricas;
    private int hrs_practicas;
    private int hrs_periodo;
    private double creditos;
    private int id_asignacion;
    private String id_plan;
    private int id_grupo;
    private int nivel_academico;
    private String id_estado;
    private String nro_resolucion;
    private String observacion;
    private int id_dct_programa_analitico;
    private String marco_referencial;
    private String justificacion;
    private String propositos;
    private String objetivo_desarrollador;
    private String objetivo_educativo;
    private String metodos_estrategias;
    private String recursos;
    private String sistema_evaluacion;
    private Date fec_registro;
    private Date fec_modificacion;
    private int ult_usuario;
    private int id_mencion;
    private String titulo;
    private int id_tipo_materia;
    private int id_programa;
    private String ciclo_materia;
    private String tipo;
    private String grado_academico;
    private String tipo_asignacion;
    private String tipo_categoria;
    private String tipo_docente;
    private String telefonocelular;
    private String formacion;
    private String Turno;
    private String modalidad_deingreso;
    private String numerodocumento;
    private int id_departamento;
    private String sistema_evaluacion_criterios;
    private Integer id_dct_programa_analitico_ant;
    private boolean aprobado;
}
