package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proveidos extends Tramites {

    /* Private Fields */
    private int id_tipo_proveido;
    private String tipo_proveido;
    private int id_proveido;
    private String proveido;

}