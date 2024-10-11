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
public class PersonaModalidadIngreso {

    private Integer id_modalidadingreso;
    private Integer id_persona_kardex;
    private Integer id_programa;
    private String modalidadingreso;
    private Date fechaingreso;
    private String url_modalidadingreso;
    private Boolean aprobado;
    private String id_estado;
    private Boolean mostrar;

}
