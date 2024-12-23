package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

import java.util.Date;

@Data
public class EvaluacionDesempeno extends Entidad {
    private int id_evaluacion_desempeno;
    private int id_resultados;
    private int id_resultados_gestion;
    private int id_programa;
    private String titulo;
    private String descripcion;
    private String elaborador;
    private String resultado;
    private String vistas;
    private Date fec_publicacion;
    private int descargas;
}
