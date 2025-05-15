package com.moxos.uab.domain.dto.response.formulariopoa;

import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaFormularioResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FormularioPoaResponse {
    private Integer id_formulario;
    private String gestion;
    private String plan_pei;
    private String area_estrategica;
    private String apertura_programatica;
    private String codigo;
    private String titulo;
    private String descripcion;
    private String eje;
    private String meta;
    private String resultado;
    private String encargado;
    private Integer id_resultados;
    private Integer id_detalle_periodos_programacion;
    private Integer id_programa;
    private Integer id_departamento;
    private List<IndicadoresEstrategicosResponse> indicadoresEstrategicosResponseList;
    private List<DetallePeriodoProgramacionResponse> detallePeriodoProgramacionResponsesList;
    private List<ObjetivoGestionPoaFormularioResponse> objetivoGestionPoaFormularioResponses;
    private List<ListView> resultadosEsperados = new ArrayList<>();
}
