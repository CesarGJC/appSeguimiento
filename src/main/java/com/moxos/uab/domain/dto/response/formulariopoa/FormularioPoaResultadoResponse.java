package com.moxos.uab.domain.dto.response.formulariopoa;

import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import com.moxos.uab.domain.dto.response.descripcionoperacionespoa.DescripcionOperacionesPoaResponse;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaResponse;
import lombok.Data;

@Data
public class FormularioPoaResultadoResponse {
    private Integer id_formulario;
    private Integer id_detalle_periodos_programacion;
    private Integer id_resultados;
    private ObjetivoGestionPoaResponse objetivoGestionPoaResponse;
    private AccionCortoPlazoPoaResponse accionCortoPlazoPoaResponse;
    private DescripcionOperacionesPoaResponse descripcionOperacionesPoaResponse;

    public FormularioPoaResultadoResponse(Integer id_formulario, Integer id_detalle_periodos_programacion, Integer id_resultados) {
        this.id_formulario = id_formulario;
        this.id_detalle_periodos_programacion = id_detalle_periodos_programacion;
        this.id_resultados = id_resultados;
    }
}
