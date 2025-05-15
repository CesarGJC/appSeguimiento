package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ObjetivoEstrategia extends Entidad {
    private int id_objetivo_estrategica;
    private int id_area_estrategica;
    private String objetivo_estrategica;
    private int orden;

}
