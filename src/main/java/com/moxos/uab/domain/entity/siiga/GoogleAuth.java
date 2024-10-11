package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class GoogleAuth {
    private Long id_google_auth;
    private Integer id_docente;
    private String secret;
    private Date fecha_solicitud;
}
