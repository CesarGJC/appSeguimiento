/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaKardex {

    private int id_persona_kardex;
    private String numerodocumento;
    private String tipodocumento;
    private String nombre;
    private String segundonombre;
    private String apellidopaterno;
    private String apellidomaterno;
    private String imagen;
    private Date fechanacimiento;
    private String direccion;
    private String telefonocelular;
    private String estadocivil;
    private String correoinsitucional;
    private int id_localidad;
    private String nua;
    private String tiponua;
    private Boolean sexo;
    private String detalle_sexo;
    private Date fechacontratoinicial;
    private Boolean sindicato;
    private Boolean jubilado;
    private String ren;
    private Boolean discapacidad;
    private String numerolibreta;
    private String numerodeseguro;
    private Date fechaingresodocente;
    private Date fechaexpiracioncarnet;
    private String matriculalibreta;
    private String escalon;
    private String aserviciomilitar;
    private String nrodiscpacitado;
    private int ult_usuario;
    private int id_banco;
    private String cuentacorriente;
    private int id_nivelestudio;
    private int id_profesiones;
    private int id_colegio_profesionales;
    private String numeroregistroprofesionales;
    private Date fechatituloprofesion;
    private Boolean ley1178;
    private String nrotitulo;
    private String promedio;
    private String idiomanativo;
    private String imagelibretamilitar;
    private String imagecarnetidentidad;
    private Date declaracionjurada;
    private String declaracionjurabienesrentas;
    private Date fechacurso1178;
    private String id_estado;
    private String universidad;
    private Date fechaemision;
    private String numerotituloprovision;
    private Boolean sippase;
    private Date fechaemisionsippase;
    private int id_persona;
    private String prefijo_profesional;
    private String emision_documento;
    private String detalle_estadocivil;
    private String detalle_localidad;
    private String detalle_banco;
    private String detalle_sindicato;
    private String detalle_jubilado;
    private String detalle_discapacitado;
    private String detalle_nivelestudio;
    private String detalle_profesion;
    private String detalle_colegio_profesionales;
    private String detalle_sippase;
    private String detalle_ley1178;
    private String numero;
    private String alfanumerico;
    private int id_nivelestudio_posgrado;
    private Date fechaemisionposgrado;
    private String tituloposgrado;
    private String emisiontituloposgrado;
    private String imagetituloposgrado;
    private List<PersonaIdioma> personaidioma;
    private int number;
    private String nivelestudio_posgrado;

}
