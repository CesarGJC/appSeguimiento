/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

/**
 * @author FNZABALETAA
 */
public class CategoriaDocente {

    private String tipo_asignacion;
    private String sigla;
    private String materia;
    private String tipo_docente;

    public String getTipo_asignacion() {
        return tipo_asignacion;
    }

    public void setTipo_asignacion(String tipo_asignacion) {
        this.tipo_asignacion = tipo_asignacion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTipo_docente() {
        return tipo_docente;
    }

    public void setTipo_docente(String tipo_docente) {
        this.tipo_docente = tipo_docente;
    }

}
