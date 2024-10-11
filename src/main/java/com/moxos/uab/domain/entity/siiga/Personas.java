package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Personas extends Localidades {

    /* Private Fields */
    private int id_persona;
    private int id_estado_civil;
    private int id_tipo_estado_civil;
    private int id_tipo_sexo;
    private int id_sexo;
    private String dip;
    private String paterno;
    private String materno;
    private String nombres;
    private String fec_nacimiento;
    private String direccion;
    private String telefono;
    private String correo;
    private String tipo_sanguineo;
    private String estado_civil;
    private int id_colegio;
    private int gestion_egreso;
    private int id_calificacion;
    private String nombre_completo;
    private String celular;
    private String tipo_empresa_telefonica;
    private int id_tipo_empresa_telefonica;

    // MI
    private int id_pais;
    private int id_departamento;
    private int id_provincia;
    private int id_localidad;
    private String pais;
    private String departamento;
    private String provincia;
    private String localidad;
    private String tipo_sexo;
    private String tipo_estado_civil;
    private int id_tipo_estudiante;
    private String tipo_estudiante;
    private int id_tipo_graduacion;
    private String tipo_graduacion;
    private int id_tipo_institucion;
    private String tipo_institucion;
    private String colegio;
    private int id_tipo_turno;
    private String tipo_turno;
    private int id_tipo_problema;
    private String tipo_problema;
    private int id_rol;
    private int id_institucion;
    private String titulo;
    private int anio_titulacion;
    private int nro_hijos;
    private String nro_seguro_medico;
    private String id_plan;
    private int nro_dependientes;
    private int anio_egreso;
    private int id_tipo_clasificacion;
    private String tipo_clasificacion;
    private int id_tipo_clasificacion_inicial;
    private String tipo_clasificacion_inicial;
    private int id_tipo_documento;
    private String tipo_documento;
    private String observacion;
    private String numero;
    private int id_tipo_compromiso;
    private String tipo_compromiso;
    private String fec_vencimiento;
    private int id_clasificacion;
    private int id_documento;
    private int id_prs_colegio;

    private int id_item;
    private String cargo;
    private String ubicacion_organica;
    private String institucion;
    private boolean presento;

    private int id_compromiso;
    private boolean vigente;
    private boolean prorroga;
    private boolean compromiso;
    private Date fec_nacimiento2;
    private int id_clf_tipo_documento;
    private String patron;
    private int pagina;

}