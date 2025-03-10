package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesFilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.request.operaciones.ProgramasActividadesUnidadRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.FormularioActividadesResponse;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IOperacionesService {
    Response<Integer> saveOperaciones(OperacionesRequest operacionesRequest);

    GeneralResponse deleteOperaciones(OperacionesRequest operacionesRequest);

    Response<OperacionesRequest> getByid(int idOperaciones);

    Response<List<FormularioActividadesResponse>> getFormulariosActividades(FilterRequest<OperacionesFilterRequest> opcion, ProgramasActividadesUnidadRequest model);

    Response<Integer> getCantidadByTipo(FilterRequest<OperacionesFilterRequest> opcion, ProgramasActividadesUnidadRequest model);

}
