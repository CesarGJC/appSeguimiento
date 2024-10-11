package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asistenciapsa extends Postulantes {

    /* Private Fields */
    private int id_asistencia;
    private int id_postulante;
    private Date fec_inicio2;
    private String fecha;
    private String hora;
    private String lugar;
    private int nro_maquinas;
    private boolean asistencia;
    private int id_rol;
    private int ult_usuario;

}
