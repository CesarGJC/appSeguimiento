package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchAperturas;
import com.moxos.uab.domain.dto.request.aperturasprogramaticas.AperturasProgramaticasResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.aperturasprogramaticas.AperturasProgramaticasRequest;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IAperturasProgramaticasService {
    Response<Integer> saveAperturasProgramaticas(AperturasProgramaticasRequest aperturasEstrategicasRequest);

    GeneralResponse deleteAperturasProgramaticas(AperturasProgramaticasRequest aperturasEstrategicasRequest);

//    Response<List<ListView>> listarAperturasProgramaticas(String codigo);

    Response<AperturasProgramaticasResponse> getByid(int id_apertura_programatica);

    Response<List<AperturasProgramaticasResponse>> listarAperturasProgramaticasByTipo(String buscar, SearchAperturas searchAperturas, int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchAperturas searchAperturas);

    Response<AperturasProgramaticasRequest> getByidAperturasProgramaticas(int id_apertura_programatica);

    Response<List<ListView>> listAperturasProgramaticas();

    Response<List<ListView>> getListaAperturasProgramaticas();
}
