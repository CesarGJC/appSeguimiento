package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UnidadEncargada  extends Entidad {
    private int id_unidad_encargada;
    private String unidad_responsable;
    private String categoria_programatica;
    private String nombre_responsable;
}
