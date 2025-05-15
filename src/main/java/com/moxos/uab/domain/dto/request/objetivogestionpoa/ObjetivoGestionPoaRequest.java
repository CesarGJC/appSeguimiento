package com.moxos.uab.domain.dto.request.objetivogestionpoa;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ObjetivoGestionPoaRequest {
    private Integer id_objetivos_gestion_poa=0;
    @NotNull(message = "El Objetivo por gestion no puede estar vacio.")
    @NotBlank(message = "El Objetivo por gestion es obligatorio.")
    private String objetivos_gestion_poa;
    private Integer orden;
    @NotNull(message = "El detalle periodo de programacion no puede estar vacio.")
    private Integer id_detalle_periodos_programacion;
    private Integer id_formulario;
    private Integer ult_usuario;
    private Integer id_programa;
    private Integer id_departamento;
}
