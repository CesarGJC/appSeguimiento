package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.categoriaindicador.CategoriaIndicadorRequest;
import com.moxos.uab.domain.dto.request.detalleperiodoprogramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.detalleperiodoprogramacion.ParametroPeiRequest;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.categoriaindicador.CategoriaIndicadorResponse;
import com.moxos.uab.domain.dto.response.detalleperiodoprogramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IPoliticasIndicadoresAreasFacade {
    IndexViewModelFilter<AreaEstrategicaResponse, Integer> getAreaEstrategicas(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<AreaEstrategicaResponse> saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    AreasEstrategicasRequest getAreaEstrategicasModel(int idAreaEstrategica);

    GeneralResponse deleteAreaEstrategica(AreasEstrategicasRequest model);

    IndexViewModelFilter<PeiResponse, Integer> getPei(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<PeiResponse> savePei(PeiRequest pei);

    PeiRequest getPeiModel(int idPei);

    GeneralResponse deletePei(PeiRequest model);

    List<ListView> getPei();

    IndexViewModelFilter<DetallePeriodoProgramacionResponse, Integer> getDetallePeriodoProgramacion(ParametrosPaginacionBusquedaRequest<ParametroPeiRequest> busqueda);

    Response<DetallePeriodoProgramacionResponse> saveDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model);

    DetallePeriodoProgramacionRequest getDetallePeriodoProgramacionModel(int idDetallePeriodoProgramacion);

    GeneralResponse deleteDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model);

    IndexViewModelFilter<PoliticasDesarrolloResponse, Integer> getPoliticasDesarrollo(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    PoliticasDesarrolloRequest getPoliticasDesarrolloModel(int idPoliticaDesarrollo);

    List<ListView> getPoliticasDesarrollo();

    List<ListView> getAreasEstrategicas();

    List<ListView> getPoliticasDesarrollo(int idAreaEstrategica);

    List<ListView> getIndicadoresEstrategicos(int idPoliticaDesarrollo);

    List<ListView> getCatalogoIndicadores(int idIndicadorEstrategico);

    Response<PoliticasDesarrolloResponse> savePoliticasDesarrollo(PoliticasDesarrolloRequest model);

    GeneralResponse deletePoliticasDesarrollo(PoliticasDesarrolloRequest model);

    IndexViewModelFilter<IndicadoresEstrategicosResponse, Integer> getIndicadoresEstrategicos(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    IndicadoresEstrategicosRequest getIndicadoresEstrategicosModel(int idIndicadorEstrategico);

    Response<IndicadoresEstrategicosResponse> saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest model);

    GeneralResponse deleteIndicadoresEstrategicos(IndicadoresEstrategicosRequest model);

    IndexViewModelFilter<CatalogoIndicadoresResponse, Integer> getCatalogosIndicadores(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    CatalogoIndicadoresRequest getCatalogoIndicadoresModel(int idCatalogoIndicadores);

    Response<CatalogoIndicadoresResponse> saveCatalogoIndicadores(CatalogoIndicadoresRequest model);

    GeneralResponse deleteCatalogoIndicadores(CatalogoIndicadoresRequest model);

    IndexViewModelFilter<CategoriaIndicadorResponse, Integer> getCategoriaIndicador(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<CategoriaIndicadorResponse> saveCategoriaIndicador(CategoriaIndicadorRequest categoriaIndicador);

    CategoriaIndicadorRequest getCategoriaIndicadorModel(int idCategoriaIndicador);

    GeneralResponse deleteCategoriaIndicador(CategoriaIndicadorRequest model);

    List<ListView> getCategoriaIndicador();
}
