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
public class PersonaFormacionAcademica {

    private int id_formacion;
    private int id_persona_kardex;
    private String expedido;
    private Date fechaemision;
    private int id_nivelestudio;
    private String nivelestudio;
    private int id_profesiones;
    private String descripcion;
    private String url_formacion;
    private String tipotitulo;
    private String numerotitulo;
    private Boolean eseducacionsuperor;
    private String id_estado;
    private Boolean aprobado;
    private Boolean mostrar;
    private String id_programas;
    private String universidad;
}
