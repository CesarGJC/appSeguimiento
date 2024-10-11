package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instituciones extends Localidades {

    /* Private Fields */
    private int id_institucion;
    private int id_tipo_institucion;
    private int id_sede_central;
    private String institucion;
    private String sigla;
    private String actividad;
    private String instrumento_apertura;
    private String direccion;
    private String telefono;
    private String fax;
    private String url;
    private Date fec_creacion;
    private Date fec_inicio_actividades;
    private int nro_sedes;
    private String autoridad;
    private String representante_legal;
    private String correo;
    private String plan_estrategico;
    private String estatuto_organico;
    private String reglamento_investigacion;
    private String centro_investigacion_central;
    private String tipo_institucion;
    private String sede;
    private String logo;

}
