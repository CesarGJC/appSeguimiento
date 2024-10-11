package com.moxos.uab.domain.entity.die;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IndicadoresEstrategicos {
    private int id_indicador_estrategico;
    private int id_politica_desarrollo;
    private String indicador_estrategico;
    private String id_estado;
    private LocalDateTime fec_registro;
    private LocalDateTime fec_modificacion;
    private String ult_usuario;
}
