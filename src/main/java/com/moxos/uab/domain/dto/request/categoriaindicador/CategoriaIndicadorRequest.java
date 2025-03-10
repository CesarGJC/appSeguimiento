package com.moxos.uab.domain.dto.request.categoriaindicador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaIndicadorRequest {
    private Integer id_categoria = 0;
    @NotNull(message = "La descripción de la categoría no puede estar vacía.")
    @NotBlank(message = "La descripción de la categoría es obligatoria.")
    private String descripcion;
    @NotNull(message = "La abreviación no puede estar vacía.")
    @NotBlank(message = "La abreviación es obligatoria.")
    private String abreviacion;
    private Integer ult_usuario;
}
