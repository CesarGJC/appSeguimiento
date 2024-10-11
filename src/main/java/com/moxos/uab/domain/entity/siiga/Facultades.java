package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Facultades extends Universidades {

    /* Private Fields */
    private int id_facultad;
    private int id_sede;
    private String facultad;
    private String codigo;
    private String decano;

}
