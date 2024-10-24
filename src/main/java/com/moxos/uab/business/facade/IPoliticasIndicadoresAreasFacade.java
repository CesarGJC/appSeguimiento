package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IPoliticasIndicadoresAreasFacade {
    IndexViewModelFilter<AreaEstrategicaResponse, Integer> getAreaEstrategicas(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<AreaEstrategicaResponse> saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    AreasEstrategicasRequest getAreaEstrategicasModel(int idAreaEstrategica);

    GeneralResponse deleteAreaEstrategica(AreasEstrategicasRequest model);

    IndexViewModelFilter<PoliticasDesarrolloResponse, Integer> getPoliticasDesarrollo(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    PoliticasDesarrolloRequest getPoliticasDesarrolloModel(int idPoliticaDesarrollo);

    List<ListView> getAreasEstrategicas();

    List<ListView> getPoliticasDesarrollo(Integer idAreaEstrategica);

    List<ListView> getIndicadoresEstrategicos();

    List<ListView> getIndicadoresEstrategicos(Integer idPoliticasDesarrollo);

    Response<PoliticasDesarrolloResponse> savePoliticasDesarrollo(PoliticasDesarrolloRequest model);

    GeneralResponse deletePoliticasDesarrollo(PoliticasDesarrolloRequest model);

    IndexViewModelFilter<IndicadoresEstrategicosResponse, Integer> getIndicadoresEstrategicos(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    IndicadoresEstrategicosRequest getIndicadoresEstrategicosModel(int idIndicadorEstrategico);

    Response<IndicadoresEstrategicosResponse> saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest model);

    GeneralResponse deleteIndicadoresEstrategicos(IndicadoresEstrategicosRequest model);

    List<ListView> getPoliticasDesarrollo();
}
