package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.ParametroPeiRequest;
import com.moxos.uab.domain.dto.request.accionestrategica.AccionEstrategicaRequest;
import com.moxos.uab.domain.dto.request.aperturasprogramaticas.AperturasProgramaticasResponse;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.request.catalogoindicadores.ParametroAreaEstrategicaRequest;
import com.moxos.uab.domain.dto.request.categoriaindicador.CategoriaIndicadorRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.request.resultadosgestion.ResultadosGestionRequest;
import com.moxos.uab.domain.dto.request.tipoindicador.TipoIndicadorRequest;
import com.moxos.uab.domain.dto.request.unidadmedida.UnidadMedidaRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaDetalleResponse;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;
import com.moxos.uab.domain.dto.response.aperturasprogramaticas.AperturasProgramaticasRequest;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreasEstrategicasDeleteResponse;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.dto.response.categoriaindicador.CategoriaIndicadorResponse;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.objetivosestrategicos.ObjetivosEstrategicosResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionResponse;
import com.moxos.uab.domain.dto.response.tipoindicador.TipoIndicadorResponse;
import com.moxos.uab.domain.dto.response.unidadmedida.UnidadMedidaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IPoliticasIndicadoresAreasFacade {
    IndexViewModelFilter<AreaEstrategicaResponse, Integer> getAreaEstrategicas(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<AreaEstrategicaResponse> saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    AreasEstrategicasDeleteResponse getAreaEstrategicasModel(int idAreaEstrategica);

    AreasEstrategicasRequest getAreaEstrategicasDetalle(int idAreaEstrategica);

    GeneralResponse deleteAreaEstrategica(AreasEstrategicasRequest model);

    IndexViewModelFilter<PeiResponse, Integer> getPei(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<PeiResponse> savePei(PeiRequest pei);

    PeiRequest getPeiModel(int idPei);

    GeneralResponse deletePei(PeiRequest model);

    List<ListView> getPei();

    ObjetivosEstrategicosRequest getObjetivosEstrategicosModel(int idObjetivo);

    Response<ObjetivosEstrategicosResponse> saveObjetivosEstrategicos(ObjetivosEstrategicosRequest objetivosEstrategico);

    GeneralResponse deleteObjetivosEstrategicos(ObjetivosEstrategicosRequest model);

    AccionEstrategicaRequest getAccionesEstrategicasModel(int idAcciones);

    AccionEstrategicaDetalleResponse getAccionesEstrategicasDetalle(int idAcciones);

    Response<AccionEstrategicaResponse> saveAccionesEstrategicas(AccionEstrategicaRequest accionesEstrategica);

    GeneralResponse deleteAccionesEstrategicas(AccionEstrategicaRequest model);

    //Incio Resultados Gestion: Cesar
    //Incio Resultados Gestion: Cesar
    //Incio Resultados Gestion: Cesar

    ResultadosGestionRequest getResultadosGestionModel(int idResultados);

    Response<ResultadosGestionResponse> saveResultadosGestion(ResultadosGestionRequest resultadosGestion);

    GeneralResponse deleteResultadosGestion(ResultadosGestionRequest model);

    //Final Resultados Gestion: Cesar

    //Incio Evaluacion Desempeno: Cesar



    //Final Evaluacion Desempeno: Cesar
    //Final Evaluacion Desempeno: Cesar
    //Final Evaluacion Desempeno: Cesar

    IndexViewModelFilter<DetallePeriodoProgramacionResponse, Integer> getDetallePeriodoProgramacion(ParametrosPaginacionBusquedaRequest<ParametroPeiRequest> busqueda);

    Response<DetallePeriodoProgramacionResponse> saveDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model);

    DetallePeriodoProgramacionRequest getDetallePeriodoProgramacionModel(int idDetallePeriodoProgramacion);

    GeneralResponse deleteDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model);

    IndexViewModelFilter<PoliticasDesarrolloResponse, Integer> getPoliticasDesarrollo(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    PoliticasDesarrolloRequest getPoliticasDesarrolloModel(int idPoliticaDesarrollo);

    List<ListView> getPoliticasDesarrollo();

    List<ListView> getAreasEstrategica();

    List<ListView> getAreasEstrategicas(Integer id);

    List<ListView> getPlanesEstrategicosInstitcuinales();

    List<ListView> getPoliticasDesarrollo(int idAreaEstrategica);

    List<ListView> getIndicadoresEstrategicos(int idPoliticaDesarrollo);

    Response<PoliticasDesarrolloResponse> savePoliticasDesarrollo(PoliticasDesarrolloRequest model);

    GeneralResponse deletePoliticasDesarrollo(PoliticasDesarrolloRequest model);

    IndexViewModelFilter<IndicadoresEstrategicosResponse, Integer> getIndicadoresEstrategicos(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    IndicadoresEstrategicosRequest getIndicadoresEstrategicosModel(int idIndicadorEstrategico);

    Response<IndicadoresEstrategicosResponse> saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest model);

    GeneralResponse deleteIndicadoresEstrategicos(IndicadoresEstrategicosRequest model);

    IndexViewModelFilter<CatalogoIndicadoresResponse, Integer> getCatalogosIndicadores(ParametrosPaginacionBusquedaRequest<ParametroAreaEstrategicaRequest> busqueda);

    CatalogoIndicadoresRequest getCatalogoIndicadoresModel(int idCatalogoIndicadores);

    Response<Integer> saveCatalogoIndicadores(CatalogoIndicadoresRequest model);

    GeneralResponse deleteCatalogoIndicadores(Integer id);


    IndexViewModelFilter<CategoriaIndicadorResponse, Integer> getCategoriaIndicador(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<CategoriaIndicadorResponse> saveCategoriaIndicador(CategoriaIndicadorRequest categoriaIndicador);

    CategoriaIndicadorRequest getCategoriaIndicadorModel(int idCategoriaIndicador);

    GeneralResponse deleteCategoriaIndicador(CategoriaIndicadorRequest model);

    List<ListView> getCategoriaIndicador();

    IndexViewModelFilter<TipoIndicadorResponse, Integer> getTipoIndicador(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<TipoIndicadorResponse> saveTipoIndicador(TipoIndicadorRequest tipoIndicadorRequest);

    TipoIndicadorRequest getTipoIndicadorModel(int idTipoIndicador);

    GeneralResponse deleteTipoIndicador(TipoIndicadorRequest model);

    IndexViewModelFilter<UnidadMedidaResponse, Integer> getUnidadMedida(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<UnidadMedidaResponse> saveUnidadMedida(UnidadMedidaRequest unidadMedidaRequest);

    UnidadMedidaRequest getUnidadMedidaModel(int idUnidadMedida);

    GeneralResponse deleteUnidadMedida(UnidadMedidaRequest model);

    List<ListView> getTiposIndicadores();

    List<ListView> getUnidadesMedidas();

    IndexViewModelFilter<AperturasProgramaticasResponse, Integer> getAperturasProgramaticas(ParametrosPaginacionBusquedaRequest<Integer> busqueda);

    Response<AperturasProgramaticasResponse> saveAperturasProgramaticas(AperturasProgramaticasRequest aperturasProgramaticas);

    AperturasProgramaticasRequest getAperturasProgramaticasModel(int idAperturasProgramatica);

    GeneralResponse deleteAperturasProgramaticas(AperturasProgramaticasRequest model);

    List<ListView> getListaAperturasProgramaticas();

    ListView getCatalogoIndicador(Integer id);
}
