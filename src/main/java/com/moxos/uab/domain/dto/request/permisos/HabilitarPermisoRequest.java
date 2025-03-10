package com.moxos.uab.domain.dto.request.permisos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HabilitarPermisoRequest {
    @JsonProperty("id")
    private Integer idPermiso;
    @JsonProperty("habilitado")
    private Boolean habilitado;
}
