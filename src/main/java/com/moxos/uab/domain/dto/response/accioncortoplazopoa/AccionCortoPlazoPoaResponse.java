package com.moxos.uab.domain.dto.response.accioncortoplazopoa;

import lombok.Data;

@Data
public class AccionCortoPlazoPoaResponse {
    private int id_accion_corto_plazo_poa;
    private int id_objetivos_gestion_poa;
    private String accion_corto_plazo_poa;
    private String objetivos_gestion_poa;
    private int id_programa;
    private int id_departamento;
    private int id_detalle_periodos_programacion;
    private int orden;
    private int cantidad;

    public AccionCortoPlazoPoaResponse(int id_accion_corto_plazo_poa, String accion_corto_plazo_poa) {
        this.id_accion_corto_plazo_poa = id_accion_corto_plazo_poa;
        this.accion_corto_plazo_poa = accion_corto_plazo_poa;
    }

    public AccionCortoPlazoPoaResponse(int id_accion_corto_plazo_poa, String accionCortoPlazoPoa, int cantidad) {
            this.id_accion_corto_plazo_poa=id_accion_corto_plazo_poa;
            this.accion_corto_plazo_poa=accionCortoPlazoPoa;
            this.cantidad=cantidad;
    }

    public AccionCortoPlazoPoaResponse() {
    }
}
