package com.moxos.uab.domain.entity.siiga;

import java.util.Date;
import java.lang.String;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Postulantes extends Estudiantes {

    /* Private Fields */

    private int id_postulante;
    private int id_rol;
    private int id_persona;
    private String observacion;
    private String numero;
    private int id_programacion;
    // private List postulantes;
    private int id_pst_prs_colegio;
    private Date fec_nacimiento2;
    private int inscritos;
    private int habilitados;
    private String dip;
    private int inhabilitados;
    private int pre_habilitados;
    private int pre_inhabilitados;
    private int psa_habilitados;
    private int psa_inhabilitados;
    private int especial_habilitados;
    private int especial_inhabilitados;
    // aumentado para el cntrol por sede
    private int ins_sede;
    // para asistencia de postulantes
    private int num;

}
