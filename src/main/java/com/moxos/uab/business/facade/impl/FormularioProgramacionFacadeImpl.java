package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.business.service.*;
import com.moxos.uab.common.util.RequestUtils;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.request.resultadosgestion.ResultadosGestionRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResultadosResponse;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionDetalleResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FormularioProgramacionFacadeImpl implements IFormularioProgramacionFacade {
    private final IFormularioService formularioService;
    private final IDetallePeriodoProgramacionService detallePeriodoProgramacionService;
    private final IObjetivosEstrategicosService objetivosEstrategicosService;
    private final ICatalogoIndicadoresService catalogoIndicadoresService;
    private final IResultadosGestionService resultadosGestionService;
    private final IResultadosService resultadosService;

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
    public FormularioProgramacionResponse getFormularioProgramacionCabecera(Integer id) {
        var response = formularioService.getFormularioProgramacionDetalle(id).getResult();
        response.setPeriodos(detallePeriodoProgramacionService.getPeriodosPlan(response.getId_plan_pei()).getResult());
        return response;
    }


    @Override
    public FormularioProgramacionResponse getFormularioProgramacionDetalle(Integer id) {
        var response = formularioService.getFormularioProgramacionDetalle(id).getResult();
        response.setPeriodos(detallePeriodoProgramacionService.getPeriodosPlan(response.getId_plan_pei()).getResult());
        response.setFormularioResultadosResponseList(objetivosEstrategicosService.getObjetivosEstrategicos(new FormularioResultadosResponse(response.getId_formulario(), response.getId_area_estrategica(), response.getEncargado(), response.getPeriodos().size())).getResult());
        return response;

    }

    @Override
    public List<ListView> getCatalogosIndicadores(Integer id) {
        return catalogoIndicadoresService.listCatalogoIndicadoresPorArea(id).getResult();
    }

    @Override
    public Response<Integer> saveResultados(ResultadosRequest model, HttpServletRequest httpServletRequest) {
        try {
            var responseResultados = resultadosService.saveResultados(model);
            if (!responseResultados.isSuccess()) {
                return new Response<>(false, responseResultados.getMessage(), -1);
            }
            resultadosGestionService.saveResultadosGestiones(getPeriodoGestion(httpServletRequest, model.getId_plan_pei(), responseResultados.getResult()));
            return new Response<>(true, "", responseResultados.getResult());
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse updateResultados(ResultadosRequest model, HttpServletRequest httpServletRequest) {
        try {
            var responseResultados = resultadosService.saveResultados(model);
            if (!responseResultados.isSuccess()) {
                return new GeneralResponse(false, responseResultados.getMessage());
            }
            resultadosGestionService.saveResultadosGestiones(getPeriodoGestion(httpServletRequest, model.getId_plan_pei(), model.getId_resultados()));
            return new GeneralResponse(true, "");
        } catch (Exception ex) {
            return new GeneralResponse(false, ex.getMessage());
        }
    }

    @Override
    public List<ListView> getListarResultadosPorGestionFormulario(Integer idPeriodoGestion, Integer idFormulario) {
        return List.of();
    }


    @Override
    public Response<ResultadosRequest> getResultadoByid(Integer id) {
        return resultadosService.getResultadoByid(id);
    }

    @Override
    public GeneralResponse deleteResultados(ResultadosRequest model) {
        return resultadosService.deleteResultados(model);
    }

    @Override
    public List<ResultadosGestionDetalleResponse> getDetallePeriodoProgramacion(ResultadosRequest model) {
        var detallePeriodos = resultadosGestionService.getPeriodosProgramacion(model.getId_plan_pei());
        List<ResultadosGestionDetalleResponse> detalleResponses = new ArrayList<>();
        for (var item : detallePeriodos.getResult()) {
            ResultadosGestionRequest detalleResponse = new ResultadosGestionRequest();
            detalleResponse.setId_resultados(model.getId_resultados());
            detalleResponse.setId_detalle_periodos_programacion(item.getId_detalle_periodos_programacion());
            var response = resultadosGestionService.getDetalleProgramacionPorResultados(detalleResponse);
            if (response.isSuccess()) {
                var resultadosGestionResponse = response.getResult();
                resultadosGestionResponse.setDescripcionPeriodo(item.getDescripcion());
                detalleResponses.add(resultadosGestionResponse);
            } else {
                ResultadosGestionDetalleResponse resultadosGestionDetalleResponse = new ResultadosGestionDetalleResponse();
                resultadosGestionDetalleResponse.setId_resultados(model.getId_resultados());
                resultadosGestionDetalleResponse.setDescripcionPeriodo(item.getDescripcion());
                resultadosGestionDetalleResponse.setId_detalle_periodos_programacion(item.getId_detalle_periodos_programacion());
                resultadosGestionDetalleResponse.setDescripcion("0");
                resultadosGestionDetalleResponse.setId_resultados_gestion(0);
                detalleResponses.add(resultadosGestionDetalleResponse);
            }
        }
        return detalleResponses;
    }


    private List<ResultadosGestionRequest> getPeriodoGestion(HttpServletRequest httpServletRequest, int id_plan_pei, int id_resultado) {
        List<ResultadosGestionRequest> resultadosGestionDetalleResponseList = new ArrayList<>();
        var response = resultadosGestionService.getPeriodosProgramacion(id_plan_pei).getResult();
        for (var item : response) {
            ResultadosGestionRequest resultadosGestionDetalleResponse = new ResultadosGestionRequest();
            resultadosGestionDetalleResponse.setId_resultados(id_resultado);
            Integer idResultadosGestion = Integer.parseInt(httpServletRequest.getParameter("id_resultados_gestion_" + item.getId_detalle_periodos_programacion()));
            String descripcion = httpServletRequest.getParameter("descripcion_" + item.getId_detalle_periodos_programacion());
            resultadosGestionDetalleResponse.setId_resultados_gestion(idResultadosGestion);
            resultadosGestionDetalleResponse.setDescripcion(descripcion);
            resultadosGestionDetalleResponse.setId_detalle_periodos_programacion(item.getId_detalle_periodos_programacion());
            resultadosGestionDetalleResponseList.add(resultadosGestionDetalleResponse);
        }
        return resultadosGestionDetalleResponseList;
    }
}
