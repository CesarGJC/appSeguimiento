package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class CertificadosEmitidos {
    private Integer id_certificados_generados;
    private Integer id_concepto;
    private Integer id_sede;
    private Integer nro_certificado;
    private Integer gestion_certificado;
    private String nrocertificado_gestion;
    private String nro_transaccion;
    private String facultad;
    private String carrera;
    private String nivel;
    private String plan;
    private String periodo_academico;
    private Integer ru;
    private String ci;
    private String estudiante;
    private String id_estado;
    private String observacion;
    private Integer reimpresiones;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer ult_usuario;

}
