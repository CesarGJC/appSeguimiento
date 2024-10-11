package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class DetalleReporte {
    private String tramite;
    private Date fec_registro;
    private String estudiante;
    private int ru;
    private String carrera;
    private String nrocertificado_gestion;
    private String nro_transaccion;
    private String usuarios;

    private int gestion;
    private String tipo;
    private int cantidad;
}
