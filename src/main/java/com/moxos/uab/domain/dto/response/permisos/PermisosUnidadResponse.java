package com.moxos.uab.domain.dto.response.permisos;

import lombok.Data;

@Data
public class PermisosUnidadResponse {
    private Integer id_permisos;
    private boolean habilitado ;
    private String unidad;
}
