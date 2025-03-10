package com.moxos.uab.domain.dto.request.operaciones;

import lombok.Data;

@Data
public class ProgramasActividadesUnidadRequest {
    private Integer id;
    private Integer id_facultad = 0;
    private Integer id_programa = 0;
    private Integer id_departamento = 0;
    private Integer tipoUnidad = 1;
}
