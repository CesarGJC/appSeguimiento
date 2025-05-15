package com.moxos.uab.domain.dto.response.trimestre;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TrimestreResponse {
    private double programado;
    private double ejecutado;
}
