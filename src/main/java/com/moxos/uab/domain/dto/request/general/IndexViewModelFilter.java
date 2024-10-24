package com.moxos.uab.domain.dto.request.general;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class IndexViewModelFilter<TDataGrid, TOption> extends BaseViewModel {

    private List<TDataGrid> lista;
    private int mostrar;
    private List<SelectListItemDto> mostrarElementos;
    private String opcion;
    private List<SelectListItemDto> opciones;
    private String buscar;
    private String tipo;
    private int cantidad;
    private TOption option;
}
