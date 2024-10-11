package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class ReporteCertificacion {
    private String facultad;
    private String carrera;
    private String sede;
    private Integer id_concepto;
    private String concepto;
    private Integer gestion;
    private Integer cantidad;

    private Date inicio;
    private Date fin;
}
