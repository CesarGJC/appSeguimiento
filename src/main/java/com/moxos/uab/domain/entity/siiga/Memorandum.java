package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Memorandum extends Asignaciones {

    /* Private Fields */
    private int id_memo;
    private int id_sede;
    private int id_asignacion;
    // private int id_estado;
    private int nro_memo;
    private int gestion;
    private Date fecha;

    private int ult_usuario;

}
