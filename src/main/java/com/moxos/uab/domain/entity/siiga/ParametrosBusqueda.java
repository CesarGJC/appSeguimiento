/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ParametrosBusqueda {

    private String buscar;
    private int sede;
    private int id_estudiante;
    private int id_materia;

    private int gestion;
    private Date fechainicio;
    private Date fechafin;
}
