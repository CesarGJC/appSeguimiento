/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import lombok.Data;

import java.util.Date;

@Data
public class Cronograma {

    private int id_prg_a_cronograma;
    private int id_dct_programa_analitico;
    private String tipo_de_clase;
    private String titulo_de_clase;
    private String tiempo_a_emplear;
    private String observaciones;
    private String id_estado;
    private Date fec_registro;
    private Date fec_modificacion;
    private int ult_usuario;
    private Date fecha;

}
