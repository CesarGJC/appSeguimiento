package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Campos extends Dominios {

    /* Private Fields */
    private int columnas;
    private int filas;
    private int caracteres;
    private String id_tipo_validacion;
    private String tipo_validacion;
    private int nro_fila;
    private int nro_columna;
    private String rango1;
    private String rango2;
    private String formula;
    private boolean referencia;
    private boolean operacion;
    private String id_tipo_permiso;
    private String tipo_permiso;
    private String campos_suma;
    private String cadena_1;
    private String cadena;
    private String tablita;
    private String valor;
    // private List lista;
    private String campos;
    private String permiso;
    private boolean habilitado;
    private int pagina;
    private String campos2;

}