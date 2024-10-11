package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enlaces extends Categorias {

    /* Private Fields */
    private int id_enlace;
    private String enlace;
    private String ruta;
    private int orden;
    private String tabla;
    private String permiso;
    private int id_enlace_padre;
    private int nivel;

}