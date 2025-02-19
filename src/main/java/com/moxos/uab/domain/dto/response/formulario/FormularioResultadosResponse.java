package com.moxos.uab.domain.dto.response.formulario;

import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;
import com.moxos.uab.domain.dto.response.objetivosestrategicos.ObjetivosEstrategicosResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosResponse;
import lombok.Data;

@Data
public class FormularioResultadosResponse {
    private Integer id_formulario;
    private Integer id_area_estrategica;
    private Integer columnas;
    private String encargado;
    private Integer cantidad;
    private ObjetivosEstrategicosResponse objetivoEstrategiaResponse;
    private AccionEstrategicaResponse accionEstrategicaResponse;
    private ResultadosResponse resultadosResponse;

    public FormularioResultadosResponse(Integer id_formulario, Integer id_area_estrategica, String encargado, Integer columnas) {
        this.id_formulario = id_formulario;
        this.id_area_estrategica = id_area_estrategica;
        this.encargado = encargado;
        this.columnas = columnas;
    }
}
