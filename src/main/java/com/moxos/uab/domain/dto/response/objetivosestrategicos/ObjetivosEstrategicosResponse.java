package com.moxos.uab.domain.dto.response.objetivosestrategicos;

import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ObjetivosEstrategicosResponse {
    private int id_objetivos_estrategicos;
    private int id_area_estrategica;
    private String area_estrategica;
    private String objetivos_estrategicos;
    private String orden;
    private Integer cantidad;
    private List<AccionEstrategicaResponse> accionEstrategicas;

    public ObjetivosEstrategicosResponse(int id_objetivos_estrategicos, String objetivos_estrategicos, Integer cantidad) {
        this.id_objetivos_estrategicos = id_objetivos_estrategicos;
        this.objetivos_estrategicos = objetivos_estrategicos;
        this.cantidad = cantidad;
    }
}
