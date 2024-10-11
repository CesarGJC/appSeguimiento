/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Data;

@Data
public class FormasOrganizacion {

    private Integer id_prg_a_formas;
    private String tipo_forma;
    private String grupo_forma;
    private String sigla_formas;
    private String formas;
    private String id_estado;
    private Date fec_registro;
    private Date fec_modificacion;
    private Integer ult_usuario;

}
