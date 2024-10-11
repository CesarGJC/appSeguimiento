package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class CertificadosEmitidosDetalle {
    private Integer id_notas_certificados;
    private Integer id_certificados_generados;
    private String nro_certificado;
    private String sigla;
    private String nivel;
    private String asignatura;
    private Double numeral;
    private String literal;
    private String tipo_evaluacion;
    private String periodogestion;
    private String observacion;
    private String id_estado;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer ult_usuario;
}
