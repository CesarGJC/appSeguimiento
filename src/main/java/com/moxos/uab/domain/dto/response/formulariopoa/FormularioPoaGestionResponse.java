package com.moxos.uab.domain.dto.response.formulariopoa;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class FormularioPoaGestionResponse extends FormularioPoaResponse {
    String gestion_periodo;
}
