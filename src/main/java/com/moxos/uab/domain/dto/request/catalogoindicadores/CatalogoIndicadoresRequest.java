package com.moxos.uab.domain.dto.request.catalogoindicadores;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CatalogoIndicadoresRequest {
    private int id_catalogo_indicador;
    private List<ListView> politicasDesarrollo;
    @NotNull(message = "Debe seleccionar la Politica de Desarrollo")
    private int id_politica_desarrollo;
    private List<ListView> indicador_estrategico;
    @NotNull(message = "Debe seleccionar el Indicador Estrategico")
    private int id_indicador_estrategico;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String catalogo_indicador;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String meta;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String linea_base;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String denominacion_indicador;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String formula;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String medios_verificacion;
    private int ult_usuario;
}
