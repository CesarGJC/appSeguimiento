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

    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String objetivos_estrategicos;

    @NotNull(message = "Inserte Mensaje de Validacion")
    private Integer orden;

    private Integer ult_usuario;
}
