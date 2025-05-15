package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AccionCortoPlazoPoa extends Entidad {
    private Integer id_accion_corto_plazo_poa;
    private Integer id_objetivos_gestion_poa;
    private String accion_corto_plazo_poa;

    private Integer id_programa;
    private Integer id_detalle_periodos_programacion;
    private Integer id_departamento;
    private Integer orden;
}
