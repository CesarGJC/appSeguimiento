/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Data;

@Data
public class FormasOrganizacionDistribucion {

    private Integer id_prg_a_formas;
    private Integer id_prg_a_contenido;
    private String sigla_formas;
    private Integer horas;
    private Integer id_prg_a_distribucion;
}
