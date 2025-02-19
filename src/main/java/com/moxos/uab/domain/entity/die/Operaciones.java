package com.moxos.uab.domain.entity.die;


import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

import java.util.Date;

@Data
public class Operaciones extends Entidad {
    private int id_operaciones;
    private int id_resultados_gestion;
    private Integer id_resultados;
    private int id_programa;
    private int id_departamento;
    private String operaciones;
    private String titulo;
    private String descripcion;
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
