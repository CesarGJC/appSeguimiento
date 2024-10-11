package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Memo extends Asignaciones {

    /* Private Fields */
    private int id_memo;

    private int id_asignacion;
    // private int id_estado;
    private int nro_memo;

    private int ult_usuario;

}
