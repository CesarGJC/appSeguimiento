package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IDepartamentoService {
    Response<ProgramaResponse> getDepartamento(int idDepartamento);

    Response<List<ListView>> getListaDepartamentos();
}
