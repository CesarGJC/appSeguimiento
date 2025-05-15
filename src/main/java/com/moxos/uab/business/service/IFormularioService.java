package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionPoaResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.formulariopoa.FormularioPoaGestionResponse;
import com.moxos.uab.domain.dto.response.formulariopoa.FormularioPoaResponse;

import java.util.List;

public interface IFormularioService {
    Response<List<FormularioResponse>> getFormularios(FilterRequest<FormularioFilterRequest> buscar);

    Response<Integer> getCantidadByTipo(FilterRequest<FormularioFilterRequest> buscar);

    Response<Integer> saveFormularioProgramacion(FormularioRequest model);

    Response<FormularioRequest> getByid(int id);

    GeneralResponse deleteFormulario(Integer id);

    Response<FormularioProgramacionResponse> getFormularioProgramacionDetalle(int id);

    Response<FormularioProgramacionPoaResponse> getFormularioProgramacionDetallePorPeriodoGestion(int id, int idDetallePeriodoGestion);

    Response<FormularioPoaResponse> getFormularioProgramacionPoaDetalle(int id);

    Response<FormularioPoaGestionResponse> getFormularioProgramacionPoaDetallePorPeriodoGestion(int id, int idDetallePeriodoGestion);

    Response<List<FormularioResponse>> getFormularioPlan(Integer id);

    Response<List<FormularioResponse>> getListaFormularioHabilitados(Integer idUnidad, Integer idTipoUnidad, Integer idPlanPei);
}
