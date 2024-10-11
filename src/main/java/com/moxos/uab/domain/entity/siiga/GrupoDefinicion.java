package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;
@Data
public class GrupoDefinicion {
    private Integer id_definicion_evaluaciones_evaluacion;
    private Integer id_tipo_nota;
    private Integer id_programa;
    private Integer id_tipo_evaluacion;
    private String id_estado;
    private Integer ponderacion;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer id_rol;
    private Integer ult_usuario;
}
