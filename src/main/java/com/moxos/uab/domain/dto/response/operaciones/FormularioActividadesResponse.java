package com.moxos.uab.domain.dto.response.operaciones;

import lombok.Data;

import java.util.Date;

@Data
public class FormularioActividadesResponse {
    private Integer id_operaciones_actividad;
    private String operaciones;
    private String titulo;
    private String descripcion;
    private String elaborador;
    private String resultado;
    private String vistas;
    private Date fec_publicacion;
    private Integer descargas;
    private String resultados_esperados;
    private String indicador;
    private String apertura_programatica;
    private Integer id_formulario;
    private String ruta;
    private String id_programa;
    private String documento;
}
