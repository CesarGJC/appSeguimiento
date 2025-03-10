package com.moxos.uab.domain.dto.request.politicasdesarrollo;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PoliticasDesarrolloRequest {
    private int id_politica_desarrollo;
    private List<ListView> areasEstrategicas;
    @NotNull(message = "Debe seleccionar el Area Estrategica")
    private int id_area_estrategica;
    @NotNull(message = "La Politica de Desarrollo no puede estar vacía.")
    @NotBlank(message = "La Politica de Desarrollo es obligatoria. ")
    private String politica_desarrollo;
    private int ult_usuario;
}
