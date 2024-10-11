package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IPoliticasDesarrolloService {
    GeneralResponse savePoliticasDesarrollo(PoliticasDesarrolloRequest pd);

    GeneralResponse updatePoliticasDesarrollo(PoliticasDesarrolloRequest pd);

    GeneralResponse deletePoliticasDesarrollo(PoliticasDesarrolloRequest pd);

    Response<List<PoliticasDesarrolloResponse>> selectPoliticasDesarrollo();

    Response<List<ListView>> listPoliticasDesarrollo();

    Response<PoliticasDesarrolloRequest> getByid(int id_politica_desarrollo);
}
