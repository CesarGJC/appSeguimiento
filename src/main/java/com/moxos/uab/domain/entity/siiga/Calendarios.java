package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calendarios extends Departamentos {

    /* Private Fields */
    private int id_programa;
    private String tabla;
    private String fec_inicio;
    private String fec_fin;
    private int id_docente;
    private String tipo_evaluacion;
    private String programa;
    private String tipo_nota;
    private int nro_tipo_nota;
    private Date fecha_inicio;
    private Date fecha_limite;
    private String tipo;
}
