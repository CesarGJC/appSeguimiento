package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TramitesCertificacion {
    private Integer id_tramitesacademicoscertificacion;
    private Integer id_estudiante;
    private String numerotramiteacademico;
    private String nombrecompletoapoderado;
    private String direccionapoderado;
    private String telefonoapoderado;
    private String correoapoderado;
    private String numeropodernotariado;
    private String observacion;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer id_rol;
    private Integer ult_usuario;
    private Integer numero;
    private Integer gestion;


    private Integer id_persona;
    private String dip;
    private String programa;
    private String tipo_estudiante;
    private String nombre_completo;
    private String id_estado;
    private List<DetalleTramiteCertificacion> detalle;
}
