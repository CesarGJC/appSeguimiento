package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class Adjuntos extends Entidad {
    private int id_adjunto;
    private int id_evaluacion_desempeno;
    private String nro_documento;
    private String tipo_documento;
}
