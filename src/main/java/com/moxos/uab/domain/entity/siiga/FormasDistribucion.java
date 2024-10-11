/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Data;

@Data
public class FormasDistribucion {
    private Integer id_prg_a_distribucion;
    private Integer id_dct_programa_analitico;
    private Integer id_prg_a_contenido;
    private Integer id_prg_a_formas;
    private Integer horas;
    private String id_estado;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer ult_usuario;
}
