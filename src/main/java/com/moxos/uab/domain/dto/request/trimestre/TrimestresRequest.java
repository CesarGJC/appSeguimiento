package com.moxos.uab.domain.dto.request.trimestre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrimestresRequest {
    private Integer id_trimestre;
    private String trimestre;
    private Integer programado;
    private Integer id_descripcion_operaciones_poa;
}
