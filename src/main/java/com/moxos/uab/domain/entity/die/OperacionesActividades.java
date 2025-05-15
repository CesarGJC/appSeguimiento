package com.moxos.uab.domain.entity.die;


import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class OperacionesActividades extends Entidad {
    private int id_operaciones_actividad;
    private int id_descripcion_operaciones_poa;
    private String operaciones;
    private String titulo;
    private String descripcion;
    private Integer id_trimestre;
    private double progreso;
    private String elaborador;
    private String resultado;
    private Integer vistas;
    private Date fec_publicacion;
    private int descargas;

    private String gestion;
    private String resultados_esperados;
    private String indicador;
    private Integer id_formulario;
    private String apertura_programatica;
    private int id_detalle_periodos_programacion;

    private String resultados_gestion;
    private String programa;
    private String departamento;
    private String ruta;
    private String documento;

}
