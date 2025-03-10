package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
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

import java.util.List;

public interface IActividadesProgramadasFacade {

    OperacionesRequest getOperacionesRequest(OperacionesRequest model);

    Response<Integer> saveOperaciones(OperacionesRequest operacionesRequest);

    GeneralResponse deleteOperaciones(OperacionesRequest model);

    IndexViewModelFilter<FormularioActividadesResponse, Integer> getActividadesProgramacion(ParametrosPaginacionBusquedaRequest<FilterRequest<OperacionesFilterRequest>> busqueda, ProgramasActividadesUnidadRequest model);


    FormularioProgramacionResponse getFormularioPorId(int idFormulario);

    OperacionesRequest crearOperacionesRequest(OperacionesRequest model);


    ResultadosDetalleResponse getResultadoPorPeriodoGestionPorId(Integer idResultado, Integer idPeriodoGestion);

    ProgramaResponse getProgramaFacultad(int idDepartamento, int idPrograma);

    List<ListView> getListaPrograma(Integer idFacultad);
}
