package com.moxos.uab.domain.dto.request.resultados;

import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.ResultadosGestion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ResultadosRequest {
    private Integer id_resultados = 0;
    private Integer id_acciones_estrategica;
    private Integer id_area_estrategica;
    @NotBlank(message = "Debe ingresar el detalle")
    private String descripcion;
    @NotBlank(message = "Debe ingresar la formula")
    private String formula;
    @NotBlank(message = "Debe ingresar la linea base")
    private String linea_base;
    @NotBlank(message = "Debe ingresar la meta")
    private String meta_base;
    @NotBlank(message = "Debe ingresar la fuente de informacion")
    private String fuente_informacion;
    @NotNull(message = "Debe ingresar el indicador")
    private Integer id_catalogo_indicador_pei;
    private List<ListView> catalogosIndicadores;
    private Integer id_formulario;
    private Integer ult_usuario;
    private Integer id_plan_pei;
}
