package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Actividades extends Principal {

    /* Private Fields */
    private int id_proceso;
    private String proceso;
    private int id_actividad;
    private String actividad;
    private int id_rol;
    private String rol;
    private int duracion;
    private int orden;
    private int id_tipo_actuacion;
    private String tipo_actuacion;
    private String actuacion;
    private int id_ubicacion_organica;
    private String ubicacion_organica;
    private boolean alerta;
    private boolean puente;
    private int correlativo;
    private int id_tipo_alerta;
    private String tipo_alerta;
    private int id_tipo_proceso;
    private String tipo_proceso;
    private int id_ubicacion_organica_padre;
    private String ruta;
    private int id_tipo_duracion;
    private String tipo_duracion;
    private boolean fin_flujo;

    private int id_form;
    private String form;
    private String codigo_proceso;

}