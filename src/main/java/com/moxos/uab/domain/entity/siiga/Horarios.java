package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Horarios extends Grupos {
    // extends Grupos {

    // Seccion Private
    private int id_dia;
    private int id_hora;
    private int id_aula;
    private int nro_aulas;
    private int asignada;
    private String aula;
    private String hora;
    private String dia;
    //private List aulas;
    private Date hora_inicio;
    private Date hora_fin;
    private int id_rol;
    private int id_tipo_aula;
    // Seccion Public

}