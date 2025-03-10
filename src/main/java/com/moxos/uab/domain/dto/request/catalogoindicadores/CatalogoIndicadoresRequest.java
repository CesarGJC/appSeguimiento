package com.moxos.uab.domain.dto.request.catalogoindicadores;

import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;


@Data
public class CatalogoIndicadoresRequest {
    private Integer id_catalogo_indicador_pei = 0;
    private Integer id_area_estrategica;
    @NotNull(message = "Debe ingresar la descripcion de la denominacion")
    @NotBlank(message = "Debe ingresar la descripcion de la denominacion")
    private String denominacion_indicador;
    @NotNull(message = "Debe seleccionar la unidad")
    private Integer id_unidad_medida;
    private List<ListView> tiposUnidades;

    @NotNull(message = "Debe seleccionar el tipo de indicador")
    private Integer id_tipo_indicador;
    private List<ListView> tiposIndicadores;

    @NotNull(message = "Debe seleccionar el tipo de categoria")
    private Integer id_categoria;
    private List<ListView> categorias;

    @NotNull(message = "La Articulacion no puede estar vacía.")
    @NotBlank(message = "La Articulacion es obligatoria. ")
    private String articulacion;

    @NotNull(message = "Debe ingresar la descripcion de la articulacion")
    @NotBlank(message = "Debe ingresar la descripcion de la articulacion")
    @Pattern(regexp = "\\d+", message = "El campo debe contener solo números")
    private String codigo = "0";
    private Integer ult_usuario;
}
