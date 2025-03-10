package com.moxos.uab.domain.dto.request.accionestrategica;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AccionEstrategicaRequest {
    private int id_acciones_estrategica;
    @NotNull(message = "Debe seleccionar la Area Estrategica")
    private Integer id_objetivos_estrategicos;
    private List<ListView> objetivosestrategicos;
    private Integer id_formulario;
    @NotNull(message = "La Área Estratégica no puede estar vacía.")
    @NotBlank(message = "La Área Estratégica es obligatoria. ")
    private String acciones_estrategica;
    @NotNull(message = "Debe ingresar el Orden en el que se mostrara el Area Estrategica")
    private Integer orden;
    private Integer ult_usuario;
}
