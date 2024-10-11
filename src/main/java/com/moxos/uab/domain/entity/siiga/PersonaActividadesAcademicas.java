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
public class PersonaActividadesAcademicas {

    private Integer id_activades_academicas;
    private Integer id_persona_kardex;
    private String tipo_documento;
    private String detalle_documento;
    private String tipo_de_actividad;
    private String gestion;
    private String mes;
    private String descripcion_actividad;
    private Boolean aprobado;
    private Boolean mostrar;
    private String id_estado;
    private String url_actividades_academicas;
}
