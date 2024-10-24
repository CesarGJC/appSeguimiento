package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchPoliticas;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.siiga.ReporteCertificacion;

import java.util.List;

public interface IPoliticasDesarrolloService {
    Response<PoliticasDesarrolloResponse> savePoliticasDesarrollo(PoliticasDesarrolloRequest pd);

    GeneralResponse deletePoliticasDesarrollo(PoliticasDesarrolloRequest pd);

    Response<List<PoliticasDesarrolloResponse>> selectPoliticasDesarrollo();

    Response<List<ListView>> listPoliticasDesarrollo();

    Response<List<ListView>> listPoliticasDesarrolloPorArea(Integer idAreaEstrategica);

    Response<PoliticasDesarrolloRequest> getByid(int id_area_estrategica);

    Response<List<PoliticasDesarrolloResponse>> listarPoliticasDesarrolloByTipo(String buscar, SearchPoliticas searchAreas, int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchPoliticas searchAreas);


}
