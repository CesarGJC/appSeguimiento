package com.moxos.uab.domain.dto.request.general;

import lombok.Data;

@Data
public class BaseViewModel {
    private int paginaActual;
    private int totaldeRegistros;
    private int registrosporPagina;
}
