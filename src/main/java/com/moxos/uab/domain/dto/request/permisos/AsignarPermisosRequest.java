package com.moxos.uab.domain.dto.request.permisos;

import lombok.Data;

@Data
public class AsignarPermisosRequest {
    private Integer id_tipo_unidad;
    private Integer id_formulario;
    private Integer[] id_unidad;
    private int ult_usuario;
}
