package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dominios extends Actividades {

    /* Private Fields */
    private int id_dominio;
    private String campos;
    private int id_dominio_padre;
    private String dominio;
    private boolean privado;
    private String dominio_padre;
    private int id_tipo_dominio;
    private String tipo_dominio;
    private int id_form;
    private int id_campo;
    private String form;
    private String campo;
    private String tabla;
    private int id_tupla;
    private String tupla;
    private int id_tupla_padre;
    private String tupla_padre;
    private int seleccionado;
    private boolean obligatorio;
    private String primario;

}