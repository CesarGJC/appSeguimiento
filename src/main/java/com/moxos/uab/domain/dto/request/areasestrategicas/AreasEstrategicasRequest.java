package com.moxos.uab.domain.dto.request.areasestrategicas;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AreasEstrategicasRequest {
    private int id_area_estrategica = 0;
    @NotNull(message = "Debe ingresar la gestion del area estrategica segun el PEI")
    private Integer id_plan_pei;
    private List<ListView> planesPei;

    @NotNull(message = "La Área Estratégica no puede estar vacía.")
    @NotBlank(message = "La Área Estratégica es obligatoria.")
    private String area_estrategica;

    @NotNull(message = "El orden no puede estar vacío.")
    private Integer orden;

    private Integer ult_usuario;
}
