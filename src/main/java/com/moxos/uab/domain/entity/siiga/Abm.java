package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Abm extends Principal {

    /* Private Fields */
    private int id_campo;
    private int id_tabla;
    private String tabla;
    private String etiqueta;
    private String campo;
    private String permiso;
    private String campo_padre;
    private String tipo_dato;
    private int orden;
    private String sql;
    private String valores;
    private String codigo;
    private String detalle;

    // INICIO JOJO \\
    private int id_componente;
    private String tabla_foranea;
    private String id_campo_foraneo;
    private String campo_foraneo;
    private String condicion;
    //private List combo;
    private int columnas;
    private int filas;
    private int caracteres;
    private int x;
    private int y;
    private String tag;
    private int pagina;
    // FIN JOJO \\
    private int id_campo_condicion;
    private int id_consulta;
    private String consulta;
    private String titulo;
    private String etiquetas;
    //private List tablas_dibrep;
    //private List campos_dibrep;
    private int id_foranea;
    private String id_campos;
    private String cabezas;
    private String sumas;
    private String alias;
    private boolean padre;
    private boolean glosa;
    private String descripcion;
    private String[] tuplaDatos;
    /* JavaBeans Properties */

}