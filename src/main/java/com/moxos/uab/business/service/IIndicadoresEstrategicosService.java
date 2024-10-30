package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchIndicadores;
import com.moxos.uab.common.enums.SearchPoliticas;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IIndicadoresEstrategicosService {

    Response<IndicadoresEstrategicosResponse> saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos);

    GeneralResponse deleteIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos);

    Response<List<IndicadoresEstrategicosResponse>> selectIndicadoresEstrategicos() ;

    Response<List<ListView>> listIndicadoresEstrategicos();

    Response<List<ListView>> listIndicadoresEstrategicosPorPolitica(Integer idPoliticaDesarrollo);

    Response<IndicadoresEstrategicosRequest> getById(int id_indicador_estrategico) ;

    Response<List<IndicadoresEstrategicosResponse>> listarIndicadoresEstrategicosByTipo(String buscar, SearchIndicadores SearchPoliticas , int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchIndicadores SearchPoliticas);

}
