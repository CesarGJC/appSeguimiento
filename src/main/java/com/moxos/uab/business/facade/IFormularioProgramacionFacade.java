package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;

public interface IFormularioProgramacionFacade {
    IndexViewModelFilter<FormularioResponse, Integer> getFormularioProgramacion(ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>> busqueda);

    Response<Integer> saveFormularioProgramacion(FormularioRequest model);

    FormularioRequest getFormularioProgramacionModel(Integer id);

    GeneralResponse deleteFormulario(Integer id);

    FormularioProgramacionResponse getFormularioProgramacionCabecera(Integer id);

    FormularioProgramacionResponse getFormularioProgramacionDetalle(Integer id);

}
