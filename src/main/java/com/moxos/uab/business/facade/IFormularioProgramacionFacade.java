package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionDetalleResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IFormularioProgramacionFacade {

    IndexViewModelFilter<FormularioResponse, Integer> getFormularioProgramacion(ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>> busqueda);

    Response<Integer> saveFormularioProgramacion(FormularioRequest model);

    FormularioRequest getFormularioProgramacionModel(Integer id);

    GeneralResponse deleteFormulario(Integer id);

    FormularioProgramacionResponse getFormularioProgramacionCabecera(Integer id);

    FormularioProgramacionResponse getFormularioProgramacionDetalle(Integer id);

    List<ListView> getCatalogosIndicadores(Integer id);

    Response<Integer> saveResultados(ResultadosRequest model, HttpServletRequest httpServletRequest);

    Response<ResultadosRequest> getResultadoByid(Integer id);

    GeneralResponse deleteResultados(ResultadosRequest model);

    List<ResultadosGestionDetalleResponse> getDetallePeriodoProgramacion(ResultadosRequest model);

    GeneralResponse updateResultados(ResultadosRequest model, HttpServletRequest httpServletRequest);

    List<ListView> getListarResultadosPorGestionFormulario(Integer idPeriodoGestion,Integer idFormulario);

}
