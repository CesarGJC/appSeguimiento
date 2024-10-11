package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Clientes extends Principal {

    /* Private Fields */
    // Usuario
    private List<Roles> roles;
    private String nombres;
    private int id_rol;
    private String rol;
    private int id_nivel_acceso;
    private int id_universidad;
    private int id_facultad;
    private int id_programa;
    private int id_departamento;
    private int id_ubicacion_organica;
    private int id_almacen;
    private String almacen;
    private String filtro;
    private String permiso;
    private List<Roles> almacenes;
    // Parametros generales
    private String formato_fecha;
    private String apodo;

    private String correo;
    private String celular;
    private String imagen;

    private boolean isUsing2FA;
    private String secret;

    private boolean bloqueado;
    private Date fecha_bloqueado;

}
