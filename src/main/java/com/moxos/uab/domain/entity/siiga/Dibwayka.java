package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dibwayka extends Principal {

    /* Private Fields */
    private int id_campo;
    private int id_dominio;
    private int id_proceso;
    private int id_tupla;
    private int id_tupla_padre;
    private int id_dominio_padre;
    private String id_tipo_validacion;
    private String id_tipo_permiso;
    private String campo;
    private String tupla;
    private String descripcion;
    // private List lista_combo;
    private String proceso;

    private int id_tabla;
    private String tabla;
    private String etiqueta;
    private String tipo_dato;
    /* JavaBeans Properties */
    private int id_consulta;
    private String consulta;
    private String etiquetas;
    private String variables;
    private String cabezas;
    private String sumas;
    private boolean glosa;
    private String componente;
    private String sql;
    private String id_campos;
    private int orden;

}