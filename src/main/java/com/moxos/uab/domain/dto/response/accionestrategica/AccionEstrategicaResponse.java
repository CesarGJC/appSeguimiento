package com.moxos.uab.domain.dto.response.accionestrategica;

import com.moxos.uab.domain.dto.response.resultados.ResultadosResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AccionEstrategicaResponse {
    private int id_acciones_estrategica;
    private int id_objetivos_estrategicos;
    private String objetivo_estrategica;
    private String acciones_estrategica;
    private int orden;
    private int cantidad;

    public AccionEstrategicaResponse(int id_acciones_estrategica, String acciones_estrategica, int cantidad) {
        this.id_acciones_estrategica = id_acciones_estrategica;
        this.acciones_estrategica = acciones_estrategica;
        this.cantidad = cantidad;
    }

    public AccionEstrategicaResponse(int id_acciones_estrategica, String acciones_estrategica) {
        this.id_acciones_estrategica = id_acciones_estrategica;
        this.acciones_estrategica = acciones_estrategica;
    }
}
