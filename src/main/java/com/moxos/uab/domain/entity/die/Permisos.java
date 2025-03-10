package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class Permisos extends Entidad {
    private Integer id_permisos;
    private Integer id_unidad;
    private Integer id_tipo_unidad ;
    private Integer id_formulario ;
    private boolean habilitado ;

    private String unidad;
    private Integer id_plan_pei;
}
