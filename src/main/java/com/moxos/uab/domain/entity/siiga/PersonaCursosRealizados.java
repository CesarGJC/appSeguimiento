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
public class PersonaCursosRealizados {

    private int id_persona_kardex;
    private int id_cursos_realizados;
    private String expedido_institucion;
    private Date fechapresentacion;
    private String nrotitulo;
    private String detalle;
    private String url_cursos;
    private String horas_academicas;
    private String tipo_eventos;
    private String duracion;
    private String id_estado;
    private Boolean aprobado;
    private String tipoorganizacion;
    private Boolean mostrar;
    private String objetivo_curso;
}
