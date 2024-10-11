package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class Entidad {
    private String id_estado = "A";
    private Date fec_modificacion;
    private Date fec_registro;
    private int ult_usuario;
    private int id_rol;
}
