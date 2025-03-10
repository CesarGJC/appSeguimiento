package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IProgramaSevice {
    Response<ProgramaResponse> getPrograma(int idPrograma);

    Response<List<ListView>> getListaProgramas(int idFacultad);

    Response<List<ListView>> getListaProgramas();
}
