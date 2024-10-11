package com.moxos.uab.domain.entity.siiga;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Principal implements Serializable {

    /* Private Fields */
    private int id_usuario;
    private int id_rol;
    private String id_estado;
    private String estado;
    private int gestion;
    private int periodo;
    private Date fec_registro;
    private Date fec_modificacion;
    private int ult_usuario;
    private String nombre_usuario;
    private String fechita;
    private String nombres;
    private int id_universidad;
    private int id_facultad;
    private int id_programa;
    private String apodo;
    private String clave;
}
