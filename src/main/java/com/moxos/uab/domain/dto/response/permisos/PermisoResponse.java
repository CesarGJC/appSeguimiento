package com.moxos.uab.domain.dto.response.permisos;

import lombok.Data;

@Data
public class PermisoResponse {
    private Integer id_permisos;
    private Integer id_unidad;
    private Integer id_tipo_unidad ;
    private Integer id_formulario ;
    private boolean habilitado ;
}
