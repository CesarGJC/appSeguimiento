package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class ObjetivoEstrategicos extends Entidad {
    private Integer id_objetivos_estrategicos;
    private Integer id_area_estrategica;
    private String objetivos_estrategicos;
    private Integer orden;

    private String descripcion;
}
