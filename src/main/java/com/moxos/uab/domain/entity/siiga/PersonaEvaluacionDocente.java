/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaEvaluacionDocente {

    private Integer id_evaluacion_docente;
    private Integer id_persona_kardex;
    private String gestion;
    private String periodo;
    private String asignatura;
    private String puntaje;
    private String url_certificado_evaluacion;
    private String id_estado;
    private Boolean aprobado;
}
