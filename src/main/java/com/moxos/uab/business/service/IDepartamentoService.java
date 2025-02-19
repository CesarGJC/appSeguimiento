package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;

public interface IDepartamentoService {
    Response<ProgramaResponse> getDepartamento(int idDepartamento);
}
