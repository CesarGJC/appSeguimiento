package com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion;

import lombok.Data;

@Data
public class PeriodosProgramacionResponse {
    private int id;
    private int gestion;

    public PeriodosProgramacionResponse(int id, int gestion) {
        this.id = id;
        this.gestion = gestion;
    }
}
