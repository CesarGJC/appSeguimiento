package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ObjetivoGestionPoa extends Entidad {
    private Integer id_objetivos_gestion_poa;
    private String objetivos_gestion_poa;
    private Integer orden;
    private Integer id_detalle_periodos_programacion;
    private Integer id_programa;
    private Integer id_departamento;
    private Integer id_formulario;
}
