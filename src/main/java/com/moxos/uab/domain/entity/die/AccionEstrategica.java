package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class AccionEstrategica extends Entidad {
    private Integer id_acciones_estrategica;
    private Integer id_objetivos_estrategicos;
    private String acciones_estrategica;
    private Integer orden;

    private String descripcion;
    private String area_estrategica;
    private String objetivos_estrategicos;
}
