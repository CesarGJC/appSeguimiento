package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class Reimpresiones {
    private Integer id_certificados_generados;
    private String observacion;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer ult_usuario;
    private String id_estado;
}
