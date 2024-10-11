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
public class PersonaProduccionCientifica {

    private Integer id_produccion_cientifica;
    private Integer id_persona_kardex;
    private String categoria;
    private String nombre_producto;
    private Date fecha_certificacion;
    private String url_portada_libro;
    private String id_estado;
    private Boolean aprobado;
    private Boolean mostrar;
    private String tipo_produccion;
    private Boolean publicado;
    private String id_programas;
    private String tipo_de_recurso;
}
