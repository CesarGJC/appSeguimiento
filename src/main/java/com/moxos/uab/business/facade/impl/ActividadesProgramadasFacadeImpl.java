package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IActividadesProgramadasFacade;
import com.moxos.uab.business.service.*;
import com.moxos.uab.common.util.RequestUtils;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesFilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.request.operaciones.ProgramasActividadesUnidadRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.operaciones.FormularioActividadesResponse;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosDetalleResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ActividadesProgramadasFacadeImpl implements IActividadesProgramadasFacade {
    private final IOperacionesService operacionesService;
    private final IConfigurationService configurationService;
    private final IFormularioService formularioService;
    private final IResultadosService resultadoService;
    private final IDescripcionOperacionesPoaService descripcionOperacionesPoaService;
    private final IDepartamentoService departamentoService;
    private final IProgramaSevice programaService;
    private final ITrimestreService trimestreService;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(ActividadesProgramadasFacadeImpl.class);

    @Override
    public Response<Integer> saveOperaciones(OperacionesRequest operacionesRequest) {
        Response<Integer> result = operacionesService.saveOperaciones(operacionesRequest);
        return result;
    }

    @Override
    public GeneralResponse deleteOperaciones(OperacionesRequest model) {
        return operacionesService.deleteOperaciones(model);
    }

    @Override
    public IndexViewModelFilter<FormularioActividadesResponse, Integer> getActividadesProgramacion(ParametrosPaginacionBusquedaRequest<FilterRequest<OperacionesFilterRequest>> busqueda, ProgramasActividadesUnidadRequest model) {
        IndexViewModelFilter<FormularioActividadesResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el número de elementos
        List<SelectListItemDto> mostrarElementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por página
        int cantidadRegistroPagina = busqueda.getMostrar();

        //Mostrar la pagina actual
        int pagina = (busqueda.getPagina() - 1) * cantidadRegistroPagina;

        //Parametro de busqueda en elementos
        FilterRequest<OperacionesFilterRequest> opcion = busqueda.getOption();
        opcion.getFilter().setPagina(cantidadRegistroPagina);
        opcion.getFilter().setNro_pagina(pagina);
        //Listo elemento a mostrar
        Response<List<FormularioActividadesResponse>> formularios = operacionesService.getFormulariosActividades(opcion, model);
        if (formularios.isSuccess()) {
            Response<Integer> totalRegistros = operacionesService.getCantidadByTipo(opcion, model);
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
    public FormularioProgramacionResponse getFormularioPorId(int idFormulario) {
        var response = formularioService.getFormularioProgramacionDetalle(idFormulario);
        if (!response.isSuccess()) {
            logger.error("No se pudo obtener el formulario con ID: " + idFormulario);
            return null;
        }
        return response.getResult();
    }

    @Override
    public OperacionesRequest crearOperacionesRequest(OperacionesRequest model) {
        var trimestre = trimestreService.getTrimestrePorDescripcion(model.getId_descripcion_operaciones_poa());
        var responseDescripcion = descripcionOperacionesPoaService.esPorcentaje(model.getId_descripcion_operaciones_poa());
        model.setPorcentaje(responseDescripcion.isSuccess());
        model.setTrimestre(trimestre);
        return model;
    }

    @Override
    public OperacionesRequest getOperacionesRequest(OperacionesRequest model) {
        return operacionesService.getByid(model.getId_operaciones_actividad()).getResult();
    }

    @Override
    public ResultadosDetalleResponse getResultadoPorPeriodoGestionPorId(Integer idResultado, Integer idPeriodoGestion) {
        var response = resultadoService.getResultadoDetalleByid(idPeriodoGestion, idResultado);
        if (!response.isSuccess()) {
            logger.error("No se pudo obtener el resultado por lagestion con ID: " + idResultado);
            return null;
        }
        return response.getResult();
    }

    @Override
    public ProgramaResponse getProgramaFacultad(int idDepartamento, int idPrograma) {
        Response<ProgramaResponse> response = idPrograma == 0 ? departamentoService.getDepartamento(idDepartamento) : programaService.getPrograma(idPrograma);
        if (!response.isSuccess()) {
            logger.error("No se pudo encontro el objeto ");
            return null;
        }
        return response.getResult();
    }

    @Override
    public List<ListView> getListaPrograma(Integer idFacultad) {
        return programaService.getListaProgramas(idFacultad).getResult();
    }

}