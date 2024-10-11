package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class Auditoria {
    private Integer id_acciones;
    private String accion;
    private String ipaddres;
    private String ubicacion;
    private String id_session;
    private Integer id_usuario;
    private String tipo_usuario;
    private Date ultima_session;
    private String app_name;
    private String macaddres;
    private String useragent;
}
