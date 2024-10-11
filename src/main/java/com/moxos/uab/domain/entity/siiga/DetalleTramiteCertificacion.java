package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class DetalleTramiteCertificacion {
    private Integer id_tramitesacademicoscertificacion;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer id_rol;
    private Integer ult_usuario;
    private String id_estado;
    private String observacion;
    private String nrorecibo;
    private Integer id_tramite;
    private Integer id_programa;
    private String tipoexamen;
    private Integer gestion;
    private Integer periodo;
    private Integer id_certificado_emitido;
    private Integer id_usuario_verificador;
    private Integer id_usuario_impresion;
    private Integer id_usuario_reimpresion;
    private Integer id_estudiante;
    private Boolean entrega;
    private Date fechaentrega;
    private Integer usuarioentrega;
    private Integer sede;
    private Integer id_transaccion;
    private Integer nivelcertificado;
    private Integer sederecibo;


    private String programa;
    private String id_plan;
    private String nombre_completo;
    private String dip;
    private String concepto;
    private Integer id_periodo;
    private Integer id_sede;
    private String facultad;
    private Integer id_tipo_grado;
    private Integer nro_certificado;
    private Integer gestion_certificado;
    private String periodo_academico;
    private String nivel;
    private String estado;

    public String getConcepto() {
        Integer idTramite = this.id_tramite == null ? 0 : this.id_tramite;
        switch (idTramite) {
            case 31:
                concepto = "Historial Academico";
                break;
            case 27:
                concepto = "Certificado de Notas";
                break;
            case 42:
                concepto = "Plan de Estudio";
                break;
            case 43:
                concepto = "Programa Analitico";
                break;
            default:
                concepto = "--";
                break;
        }

        return concepto;
    }

}
