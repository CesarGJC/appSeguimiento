package com.moxos.uab.domain.dto.request.formulario;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FormularioRequest {
    private Integer id_formulario;
    @NotNull(message = "Debe seleccionar el plan estrategico")
    private Integer id_plan_pei = -1;
    private List<ListView> planes;
    @NotNull(message = "Debe seleccionar el area estrategico")
    private Integer id_area_estrategica = -1;
    private List<ListView> areaEstrategicas;
    @NotNull(message = "Debe seleccionar el apertura programatica")
    private Integer id_apertura_programatica = -1;
    private List<ListView> aperturaProgramaticas;

    @NotBlank(message = "Debe ingresar el titulo")
    private String titulo;
    @NotNull(message = "Debe seleccionar la descripcion")
    private String descripcion;
    @NotNull(message = "Debe seleccionar el eje del plan")
    private String eje;
    @NotNull(message = "Debe seleccionar la meta del plan")
    private String meta;
    @NotNull(message = "Debe seleccionar el resultado plan")
    private String resultado;
    @NotNull(message = "Debe seleccionar el encargado del plan")
    private String encargado;
    private Integer ult_usuario;

}
