package com.moxos.uab.domain.dto.request.objetivosestrategicos;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ObjetivosEstrategicosRequest {
    private int id_objetivos_estrategicos;
    @NotNull(message = "Debe seleccionar la Area Estrategica")
    private Integer id_area_estrategica;
    private List<ListView> areasestrategicas;
    private Integer id_formulario;
    private Integer id_detalle_periodos_programacion;

    @NotNull(message = "El Objetivo Estrategico no puede estar vacio.")
    @NotBlank(message = "El Objetivo Estrategico es obligatorio.")
    private String objetivos_estrategicos;

    @NotNull(message = "El Orden no puede estar vacio.")
    private Integer orden;
    private Integer id_programa;
    private Integer id_departamento;
    private Integer ult_usuario;
}
