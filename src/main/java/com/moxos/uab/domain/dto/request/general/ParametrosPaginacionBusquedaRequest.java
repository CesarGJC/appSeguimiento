package com.moxos.uab.domain.dto.request.general;

import lombok.Data;

import java.util.Optional;
@Data
public class ParametrosPaginacionBusquedaRequest<TOptions> {
    private Integer pagina = 1;
    private Integer mostrar = 100;
    private String buscar="";
    private TOptions option;
}
