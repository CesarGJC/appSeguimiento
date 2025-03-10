package com.moxos.uab.domain.dto.request.permisos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PermisosRequest {
    private int id_permisos =0 ;
    @NotNull(message = "Debe seleccionar la unidad")
    private Integer id_unidad;
    @NotNull(message = "Debe seleccionar el tipo unidad")
    private Integer id_tipo_unidad ;
    @NotNull(message = "Debe seleccionar el formulario")
    private Integer id_formulario ;
    private boolean habilitado ;
    private Integer ult_usuario;
}
