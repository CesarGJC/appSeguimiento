package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;

import java.util.List;

public interface IFormularioService {
    Response<List<FormularioResponse>> getFormularios(FilterRequest<FormularioFilterRequest> buscar);

    Response<Integer> getCantidadByTipo(FilterRequest<FormularioFilterRequest> buscar);

    Response<Integer> saveFormularioProgramacion(FormularioRequest model);

    Response<FormularioRequest> getByid(int id);

    GeneralResponse deleteFormulario(Integer id);

    Response<FormularioProgramacionResponse> getFormularioProgramacionDetalle(int id);

    Response<List<FormularioResponse>> getFormularioPlan(Integer id);

    Response<List<FormularioResponse>> getListaFormularioHabilitados(Integer idUnidad, Integer idTipoUnidad, Integer idPlanPei);
}
