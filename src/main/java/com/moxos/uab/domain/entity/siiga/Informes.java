package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Informes extends Campos {

    /* Private Fields */
    private int id_informe;
    private String informe;
    private String descripcion;
    private String contenido;
    private int id_tramite;
    private String valor;

}