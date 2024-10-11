package com.moxos.uab.domain.entity.siiga;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tramites extends Campos {

    /* Private Fields */
    private int id_dato;
    private String valores;
    private String primarios;
    private String campos;
    private String tabla_foranea;
    private String id_campo_foraneo;
    private String campo_foraneo;
    //private List tuplas;
    private int resultado;
    private int id_tipo_proveido;
    private String tipo_proveido;
    private int para;
    private int de;
    private int id_tramite;
    private String proveido;
    private int id_actividad_actual;
    private int id_tipo_documento;
    private String tipo_documento;
    private String nombre_completo;
    private List<Usuarios> usuarios;
    private String usuario;
    private int id_actividad_minima;
    private String imagen;
    private Date fechaini;
    private Date fechafin;
    private String cargo;
    private int nro_total_tramites;
    private int nro_tramites_hoy;
    private int nro_tramites_semanal;
    private int nro_tramites_anteriores;
    private int retrocedido;
    private String dias;
    private int dia;
    private int horas;
    private int minutos;
    private int segundos;
    private int milisegundos;
    private String usuario_de;
    private String usuario_para;
    private String etiqueta;
    private Date fec_registro;
    private int pagina;
    private String fecha_ini;
    private String fecha_fin;
    private int cantidad;

    private int min;
    private int max;
    private String correlativo2;
    private int gestion;

    private String valor;
}