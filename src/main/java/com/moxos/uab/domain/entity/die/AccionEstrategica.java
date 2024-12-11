package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class AccionEstrategica extends Entidad {
    private int id_acciones_estrategica;
    private int id_objetivo_estrategica;
    private String objetivo_estrategica;
    private int orden;
}
