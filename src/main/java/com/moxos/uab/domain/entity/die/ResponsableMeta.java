package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class ResponsableMeta extends Entidad {
    private int id_responsable_meta;
    private int id_catalogo_indicador;
    private int gestion;
    private String unidad_porcentaje;
    private int programado;
    private int ejecutado;
    private double porcentaje_ejecutado;
    private int id_unidad_encargada;
}
