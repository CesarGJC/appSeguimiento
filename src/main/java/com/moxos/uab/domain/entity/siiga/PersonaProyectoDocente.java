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
public class PersonaProyectoDocente {

    private Integer id_personas_proyecto;
    private String nombreproyecto;
    private String inversion;
    private String financiador;
    private String origendelproyectoproyecto;
    private Integer gestion;
    private String estado;
    private String funcion;
    private String modalidad;
    private String duracionproyecto;
    private String tipodeproyecto;
    private Boolean representaciondirigencial;
    private String documento;
    private Boolean mostrar;
    private Integer id_persona_kardex;
    private Boolean aprobado;
    private String id_estado;
}
