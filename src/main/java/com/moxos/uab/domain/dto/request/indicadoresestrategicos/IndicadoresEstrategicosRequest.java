package com.moxos.uab.domain.dto.request.indicadoresestrategicos;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class IndicadoresEstrategicosRequest {
    private int id_indicador_estrategico;
    private List<ListView> areasEstrategicas;
    @NotNull(message = "Debe seleccionar la Area Estrategica")
    private int id_area_estrategica;
    private List<ListView> politicasDesarrollo;
    @NotNull(message = "Debe seleccionar la Politica de Desarrollo")
    private int id_politica_desarrollo;
    @NotNull(message = "El Indicador Estrategico no puede estar vacío.")
    @NotBlank(message = "El Indicador Estrategico es obligatorio.")
    private String indicador_estrategico;
    private int ult_usuario;
}
