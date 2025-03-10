package com.moxos.uab.domain.dto.response.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import lombok.Data;

import java.util.List;

@Data
public class ConfiguracionPlanInstitucionalResponse {

    @JsonProperty("periodos")
    private List<ListView> periodosPlan;
    @JsonProperty("id")
    private Integer idPeriodo;
    @JsonProperty("formularios")
    private List<FormularioResponse> formularios;
}
