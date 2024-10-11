package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notas extends Menciones {

    /* Private Fields */
    private int gestion;
    private int periodo;
    private String id_estado;
    private int nota;
    private String nro_resolucion;
    private int id_docente;
    private int id_matricula;
    private int id_materia;
    private int id_grupo;
    private String nombres;
    private int id_nota;
    private int id_departamento;
    private int id_fase;
    private int id_asignacion;
    private int id_convalidacion;
    private String folio;
    private String libro;
    private String observacion;
    private boolean rectificado;
    private String literal;
    private int hrs_periodo;
    private String tipo_evaluacion;
    private int id_tipo_materia;
}
