package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IIndicadoresEstrategicosService {
    GeneralResponse saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos);

    GeneralResponse updateIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos) ;

    GeneralResponse deleteIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos);

    Response<List<IndicadoresEstrategicosResponse>> selectIndicadoresEstrategicos() ;

    Response<List<ListView>> listIndicadoresEstrategicos(); ;

    Response<IndicadoresEstrategicosRequest> getById(int id_indicador_estrategico) ;
}
