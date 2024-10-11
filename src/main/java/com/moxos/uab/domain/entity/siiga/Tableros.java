package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tableros extends Personas {

    /* Private Fields */
    private int id_tipo_tablero;
    private String tipo_tablero;
    private int id_tipo_aviso;
    private String tipo_aviso;
    private int id_tablero;
    private String noticia;
    private String mensaje;
    private int id_rol;
    private String rol;
}