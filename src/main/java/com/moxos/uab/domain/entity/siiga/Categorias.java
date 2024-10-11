package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categorias extends Principal {

    /* Private Fields */
    private int id_categoria;
    private int id_rol;
    private String categoria;
    private String imagen;
    //private List   enlaces;
}