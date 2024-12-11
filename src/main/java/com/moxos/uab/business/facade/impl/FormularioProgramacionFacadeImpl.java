package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.business.service.IDetallePeriodoProgramacionService;
import com.moxos.uab.business.service.IFormularioService;
import com.moxos.uab.common.util.RequestUtils;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormularioProgramacionFacadeImpl implements IFormularioProgramacionFacade {
    private final IFormularioService formularioService;
    private final IDetallePeriodoProgramacionService detallePeriodoProgramacionService;

    public FormularioProgramacionFacadeImpl(IFormularioService formularioService, IDetallePeriodoProgramacionService detallePeriodoProgramacionService) {
        this.formularioService = formularioService;
        this.detallePeriodoProgramacionService = detallePeriodoProgramacionService;
    }

    @Override
    public IndexViewModelFilter<FormularioResponse, Integer> getFormularioProgramacion(ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>> busqueda) {
        IndexViewModelFilter<FormularioResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el número de elementos
        List<SelectListItemDto> mostrarElementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por página
        int cantidadRegistroPagina = busqueda.getMostrar();

        //Mostrar la pagina actual
        int pagina = (busqueda.getPagina() - 1) * cantidadRegistroPagina;

        //Parametro de busqueda en elementos
        FilterRequest<FormularioFilterRequest> opcion = busqueda.getOption();
        opcion.getFilter().setPagina(cantidadRegistroPagina);
        opcion.getFilter().setNro_pagina(pagina);
        //Listo elemento a mostrar
        Response<List<FormularioResponse>> formularios = formularioService.getFormularios(opcion);
        if (formularios.isSuccess()) {
            Response<Integer> totalRegistros = formularioService.getCantidadByTipo(opcion);
            filtro.setTotaldeRegistros(totalRegistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(formularios.getResult());
        filtro.setPaginaActual(busqueda.getPagina());
        filtro.setRegistrosporPagina(cantidadRegistroPagina);
        filtro.setMostrarElementos(mostrarElementos);
        filtro.setMostrar(cantidadRegistroPagina);
        return filtro;
    }

    @Override
    public Response<Integer> saveFormularioProgramacion(FormularioRequest model) {
        return formularioService.saveFormularioProgramacion(model);
    }

    @Override
    public FormularioRequest getFormularioProgramacionModel(Integer id) {
        return formularioService.getByid(id).getResult();
    }

    @Override
    public GeneralResponse deleteFormulario(Integer id) {
        return formularioService.deleteFormulario(id);
    }

    @Override
    public FormularioProgramacionResponse getFormularioProgramacionDetalle(Integer id) {
        var response = formularioService.getFormularioProgramacionDetalle(id).getResult();
        response.setPeriodos(detallePeriodoProgramacionService.getPeriodosPlan(response.getId_plan_pei()).getResult());
        return response;
    }
}
