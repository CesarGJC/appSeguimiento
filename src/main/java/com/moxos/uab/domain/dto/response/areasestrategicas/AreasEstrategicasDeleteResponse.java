package com.moxos.uab.domain.dto.response.areasestrategicas;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AreasEstrategicasDeleteResponse {
    private int id_area_estrategica = 0;
    private Integer id_plan_pei;
    private String area_estrategica;
    private String descripcion;
    private String orden;
}
