package com.moxos.uab.domain.dto.response.areasestrategicas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AreaEstrategicaResponse {
    private int id_area_estrategica;
    private String area_estrategica;
}
