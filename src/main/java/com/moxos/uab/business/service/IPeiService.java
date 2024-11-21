package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchPei;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IPeiService {
    Response<Integer> savePei(PeiRequest pei);

    GeneralResponse deletePei(PeiRequest pei);

    Response<List<ListView>> listarPei(String gestion);

    Response<PeiResponse> getByid(int id_plan_pei);

    Response<List<PeiResponse>> listarPeiByTipo(String buscar, SearchPei searchPei, int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchPei searchPei);

    Response<PeiRequest> getByidPei(int id_plan_pei);
}
