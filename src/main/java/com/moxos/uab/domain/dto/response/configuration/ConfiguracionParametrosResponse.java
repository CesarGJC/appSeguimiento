package com.moxos.uab.domain.dto.response.configuration;

import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import lombok.Data;

import java.util.List;

@Data
public class ConfiguracionParametrosResponse {
    private ConfigurationResponse planInstitucional;
    private ConfigurationResponse periodoGesionPlan;
    private List<FormularioResponse> formularios;
}
