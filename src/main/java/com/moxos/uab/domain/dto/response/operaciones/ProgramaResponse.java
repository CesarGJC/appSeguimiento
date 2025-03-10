package com.moxos.uab.domain.dto.response.operaciones;

import lombok.Data;

@Data
public class ProgramaResponse {
    private Integer id_programa = 0;
    private Integer id_facultad = 0;
    private String programa = "";
    private String facultad = "";
}
