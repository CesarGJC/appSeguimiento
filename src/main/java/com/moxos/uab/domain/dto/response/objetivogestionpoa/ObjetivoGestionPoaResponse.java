package com.moxos.uab.domain.dto.response.objetivogestionpoa;

import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import lombok.Data;

import java.util.List;


@Data
public class ObjetivoGestionPoaResponse {
    private int id_objetivos_gestion_poa;
    private String objetivos_gestion_poa;
    private int orden;
    private int id_detalle_periodos_programacion;
    private String detalle_periodos_programacion;
    private Integer cantidad;
    private Integer id_formulario;
    private Integer id_resultados;
    private List<AccionCortoPlazoPoaResponse> accionCortoPlazoPoa;

    public ObjetivoGestionPoaResponse(int id_objetivos_gestion_poa, String objetivos_gestion_poa, Integer cantidad) {
        this.id_objetivos_gestion_poa=id_objetivos_gestion_poa;
        this.objetivos_gestion_poa=objetivos_gestion_poa;
        this.cantidad=cantidad;
    }

    public ObjetivoGestionPoaResponse() {
    }
}
