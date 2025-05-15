package com.moxos.uab.domain.dto.response.objetivogestionpoa;

import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import com.moxos.uab.domain.dto.response.descripcionoperacionespoa.DescripcionOperacionesPoaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjetivoGestionPoaFormularioResponse {
    private Integer id_formulario;
    private Integer id_detalle_periodos_programacion;
    private Integer id_programa;
    private Integer id_departamento;
    private Integer id_resultados;
    private ObjetivoGestionPoaResponse objetivoGestionPoaResponse;
    private AccionCortoPlazoPoaResponse accionCortoPlazoPoaResponse;
    private DescripcionOperacionesPoaResponse descripcionOperacionesPoaResponse;
}
