package com.moxos.uab.domain.dto.request.unidadmedida;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UnidadMedidaRequest {
    private Integer id_unidad_medida = 0;
    @NotNull(message = "La Descripcion no puede estar vacía.")
    @NotBlank(message = "La Descripcion es obligatoria. ")
    private String descripcion;
    @NotNull(message = "La Abreviacion no puede estar vacía.")
    @NotBlank(message = "La Abreviacion es obligatoria. ")
    private String abreviacion;
    private Integer ult_usuario;
}
