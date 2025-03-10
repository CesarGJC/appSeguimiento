package com.moxos.uab.domain.dto.request.tipoindicador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoIndicadorRequest {
    private Integer id_tipo_indicador=0;
    private String descripcion;
    @NotNull(message = "La Abreviacion no puede estar vacía.")
    @NotBlank(message = "La Abreviacion es obligatoria. ")
    private String abreviacion;
    private Integer ult_usuario;
}
