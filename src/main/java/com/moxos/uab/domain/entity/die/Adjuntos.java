package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Adjuntos extends Entidad {
    private Integer id_adjunto_operaciones_actividad;
    private Integer id_operaciones_actividad;
    private String ruta;
    private String documento;
    private String tipo_documento;
    private String orden;
    private String descripcion_documento;
    private String nombre_documento;

    private String ruta_documento;
}
