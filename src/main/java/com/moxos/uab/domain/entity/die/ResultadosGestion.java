package com.moxos.uab.domain.entity.die;


import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class ResultadosGestion extends Entidad {
    private int id_resultados_gestion;
    private int id_resultados;
    private int id_detalle_periodos_programacion;
    private int id_acciones_estrategica;
    private String descripcion;
    private String formula;
    private String linea_base;
    private String meta_base;
    private String fuente_informacion;
    private String denominacion_indicador;
    private int id_unidad_medida;
    private int id_tipo_indicador;
    private int id_categoria;
    private String articulacion;
}
