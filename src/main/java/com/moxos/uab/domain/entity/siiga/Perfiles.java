package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Perfiles extends Planes {

    /* Private Fields */
    private int id_concepto;
    private int id_perfil;
    private int id_perfil_concepto;
    private int id_transaccion;
    private int id_estudiante;
    private int id_postulante;
    private int id_persona;
    private int id_persona_pst;
    private int id_programa;
    private int id_tipo_perfil;
    private int id_tipo_grado;
    private int id_tipo_clasificacion;
    private int id_proceso;
    private String id_perfil_proceso;
    private String tipo_perfil;
    private String concepto;
    private String perfil;
    private String codigo_perfil;
    private double costo;
    private double deposito;
    private double efectivo;
    private double total;
    private double pagado;
    private double descuento;
    private int remitente;
    private String nro_recibo;
    private int cantidad;
    private String proceso;
    private Date fec_pago;

    private String fecha_ini;
    private String fecha_fin;
    private String programa;

    private int id_tipo_descuento;
    private String tipo_descuento;
    private double porcentaje_descuento;
    private int id_perfil_materia;
    private int ins_sede;
    private int id_almacen;
}