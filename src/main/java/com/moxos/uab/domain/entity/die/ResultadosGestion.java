package com.moxos.uab.domain.entity.die;


import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResultadosGestion extends Entidad {
    private Integer id_resultados_gestion;
    private Integer id_resultados;
    private Integer id_detalle_periodos_programacion;
    private String descripcion;

    private String descripcionResultado;
}
