package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

import java.util.List;

@Data
public class Resultados extends Entidad {
    private Integer id_resultados;
    private Integer id_acciones_estrategica;
    private String descripcion;
    private String formula;
    private String linea_base;
    private String meta_base;
    private String fuente_informacion;
    private Integer id_catalogo_indicador_pei;
    private String denominacion_indicador;
    private Integer id_unidad_medida;
    private Integer id_tipo_indicador;
    private Integer id_categoria;
    private String articulacion;
    private Integer id_formulario;

    private Integer id_detalle_periodos_programacion;
    private String abreviacionUnidadMedida;
    private Integer id_area_estrategica;
    private String codigo;
    private List<ResultadosGestion> resultadosGestion;
    private String acciones_estrategica;
    private String categoria_indicador;
    private String tipo_indicador;
    private String unidad_medidad;
    private String meta;
    private String gestion;
    private String abreviacion;

}
