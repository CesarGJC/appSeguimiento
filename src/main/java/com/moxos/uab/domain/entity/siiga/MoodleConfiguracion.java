/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoodleConfiguracion {

    private long id_condiguracion_moodle;
    private String moodle_host;
    private String moodle_key;
    private int moodle_rol_teacher;
    private int moodle_rol_student;
    private String moodle_detalle;
    private String id_estado;
    private Date fec_registro;
    private Date fec_modificacion;
    private int ult_usuario;
}
