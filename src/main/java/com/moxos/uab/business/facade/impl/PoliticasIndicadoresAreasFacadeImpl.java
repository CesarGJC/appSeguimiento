package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.business.service.*;
import com.moxos.uab.common.enums.*;
import com.moxos.uab.common.util.RequestUtils;
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
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
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
import com.moxos.uab.persistence.siiga.PlanesDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticasIndicadoresAreasFacadeImpl implements IPoliticasIndicadoresAreasFacade {
    private final IAreasEstrategicasService areasEstrategicasService;
    private final IPoliticasDesarrolloService politicasDesarrolloService;
    private final IConfigurationService configurationService;
    private final IIndicadoresEstrategicosService indicadoresEstrategicosService;
    private final ICatalogoIndicadoresService catalogoIndicadoresService;
    private final IPeiService peiService;
    private final IDetallePeriodoProgramacionService detallePeriodoProgramacionService;
    private final ModelMapper modelMapper;
    private final IObjetivosEstrategicosService objetivosEstrategicosService;
    private final IAccionesEstrategicasService accionesEstrategicasService;
    private final PlanesDao planesDao;
    private final ICategoriaIndicadorService categoriaIndicadorService;
    private final ITipoIndicadorService tipoIndicadorService;
    private final IUnidadMedidaService unidadMedidaService;
    private final IAperturasProgramaticasService aperturasProgramaticasService;
    private final IResultadosGestionService resultadosGestionService;

    public PoliticasIndicadoresAreasFacadeImpl(IAreasEstrategicasService areasEstrategicasService, IPoliticasDesarrolloService politicasDesarrolloService, IConfigurationService configurationService, IIndicadoresEstrategicosService indicadoresEstrategicosService, ICatalogoIndicadoresService catalogoIndicadoresService, IPeiService peiService, IDetallePeriodoProgramacionService detallePeriodoProgramacionService, ModelMapper modelMapper, IObjetivosEstrategicosService objetivosEstrategicosService, IAccionesEstrategicasService accionesEstrategicasService, PlanesDao planesDao, ICategoriaIndicadorService categoriaIndicadorService, ITipoIndicadorService tipoIndicadorService, IUnidadMedidaService unidadMedidaService, IAperturasProgramaticasService aperturasProgramaticasService,  IResultadosGestionService resultadosGestionService) {
        this.areasEstrategicasService = areasEstrategicasService;
        this.politicasDesarrolloService = politicasDesarrolloService;
        this.configurationService = configurationService;
        this.indicadoresEstrategicosService = indicadoresEstrategicosService;
        this.catalogoIndicadoresService = catalogoIndicadoresService;
        this.peiService = peiService;
        this.detallePeriodoProgramacionService = detallePeriodoProgramacionService;
        this.modelMapper = modelMapper;
        this.objetivosEstrategicosService = objetivosEstrategicosService;
        this.accionesEstrategicasService = accionesEstrategicasService;
        this.planesDao = planesDao;
        this.categoriaIndicadorService = categoriaIndicadorService;
        this.tipoIndicadorService = tipoIndicadorService;
        this.unidadMedidaService = unidadMedidaService;
        this.aperturasProgramaticasService = aperturasProgramaticasService;
        this.resultadosGestionService = resultadosGestionService;
    }

    @Override
    public IndexViewModelFilter<AreaEstrategicaResponse, Integer> getAreaEstrategicas(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<AreaEstrategicaResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<AreaEstrategicaResponse>> designados = areasEstrategicasService.listarAreaEstrategicaByTipo(buscar, SearchAreas.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = areasEstrategicasService.getCantidadByTipo(buscar, SearchAreas.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public Response<AreaEstrategicaResponse> saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica) {
        Response<Integer> result = areasEstrategicasService.saveAreaEstrategica(areaEstrategica);
        return areasEstrategicasService.getByid(result.getResult());
    }

    @Override
    public AreasEstrategicasDeleteResponse getAreaEstrategicasModel(int idAreaEstrategica) {
        return areasEstrategicasService.getByidAreasEstrategicas(idAreaEstrategica).getResult();
    }

    @Override
    public AreasEstrategicasRequest getAreaEstrategicasDetalle(int idAreaEstrategica) {
        return areasEstrategicasService.getByidAreasEstrategicasDetalle(idAreaEstrategica).getResult();
    }

    @Override
    public GeneralResponse deleteAreaEstrategica(AreasEstrategicasRequest model) {
        return areasEstrategicasService.deleteAreaEstrategica(model);
    }

    @Override
    public IndexViewModelFilter<PeiResponse, Integer> getPei(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<PeiResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<PeiResponse>> designados = peiService.listarPeiByTipo(buscar, SearchPei.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = peiService.getCantidadByTipo(buscar, SearchPei.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public Response<PeiResponse> savePei(PeiRequest pei) {
        Response<Integer> result = peiService.savePei(pei);
        return peiService.getByid(result.getResult());
    }

    @Override
    public PeiRequest getPeiModel(int idPei) {
        return peiService.getByidPei(idPei).getResult();
    }

    @Override
    public GeneralResponse deletePei(PeiRequest model) {
        return peiService.deletePei(model);
    }

    @Override
    public List<ListView> getPei() {
        return List.of();
    }

    @Override
    public ObjetivosEstrategicosRequest getObjetivosEstrategicosModel(int idObjetivo) {
        var response = objetivosEstrategicosService.getByid(idObjetivo);
        return modelMapper.map(response.getResult(), ObjetivosEstrategicosRequest.class);
    }

    @Override
    public Response<ObjetivosEstrategicosResponse> saveObjetivosEstrategicos(ObjetivosEstrategicosRequest objetivosEstrategico) {
        Response<Integer> result = objetivosEstrategicosService.saveObjetivosEstrategicos(objetivosEstrategico);
        return objetivosEstrategicosService.getByid(result.getResult());
    }

    @Override
    public GeneralResponse deleteObjetivosEstrategicos(ObjetivosEstrategicosRequest model) {
        return objetivosEstrategicosService.deleteObjetivosEstrategicos(model);
    }

    @Override
    public AccionEstrategicaRequest getAccionesEstrategicasModel(int idAcciones) {
        var response = accionesEstrategicasService.getByid(idAcciones);
        return modelMapper.map(response.getResult(), AccionEstrategicaRequest.class);
    }

    @Override
    public AccionEstrategicaDetalleResponse getAccionesEstrategicasDetalle(int idAcciones) {
        var response = accionesEstrategicasService.getDetalleByid(idAcciones);
        return response.getResult();
    }

    @Override
    public Response<AccionEstrategicaResponse> saveAccionesEstrategicas(AccionEstrategicaRequest accionesEstrategica) {
        Response<Integer> result = accionesEstrategicasService.saveAccionEstrategica(accionesEstrategica);
        return accionesEstrategicasService.getByid(result.getResult());
    }

    @Override
    public GeneralResponse deleteAccionesEstrategicas(AccionEstrategicaRequest model) {
        return accionesEstrategicasService.deleteAccionEstrategica(model);
    }

    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---
    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---
    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---
    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---

    @Override
    public ResultadosGestionRequest getResultadosGestionModel(int idResultados) {
        var response= resultadosGestionService.getById(idResultados);
        return modelMapper.map(response.getResult(), ResultadosGestionRequest.class);
    }

    @Override
    public Response<ResultadosGestionResponse> saveResultadosGestion(ResultadosGestionRequest resultadosGestion) {
        Response<Integer> result = resultadosGestionService.saveResultadosGestion(resultadosGestion);
        return resultadosGestionService.getById(result.getResult());
    }

    @Override
    public GeneralResponse deleteResultadosGestion(ResultadosGestionRequest model) {
        return resultadosGestionService.deleteResultadosGestion(model);
    }


    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---
    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---
    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---
    //----Fin Resultado Gestion || Evaluacion Desempeno: Cesar---

    @Override
    public IndexViewModelFilter<DetallePeriodoProgramacionResponse, Integer> getDetallePeriodoProgramacion(ParametrosPaginacionBusquedaRequest<ParametroPeiRequest> busqueda) {

        //Clase generica para la paginacion
        IndexViewModelFilter<DetallePeriodoProgramacionResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = busqueda.getMostrar();

        //Mostrar la pagina actual
        int pagina = (busqueda.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = busqueda.getBuscar() == null ? "'%%'" : "'%" + busqueda.getBuscar().toUpperCase() + "%'";
        ParametroPeiRequest opcion = busqueda.getOption();
        //Lista elementos a mostrar
        Response<List<DetallePeriodoProgramacionResponse>> designados = detallePeriodoProgramacionService.listarDetallePeriodoProgramacionByTipo(buscar, SearchDetallePeriodo.values()[opcion.getOpcion()], cantidadderegistrosporpagina, pagina, opcion.getId());
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = detallePeriodoProgramacionService.getCantidadByTipo(buscar, SearchDetallePeriodo.values()[opcion.getOpcion()], opcion.getId());
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(busqueda.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public Response<DetallePeriodoProgramacionResponse> saveDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model) {
        return detallePeriodoProgramacionService.saveDetallePeriodoProgramacion(model);
    }

    @Override
    public DetallePeriodoProgramacionRequest getDetallePeriodoProgramacionModel(int idDetallePeriodoProgramacion) {
        var response = detallePeriodoProgramacionService.getByid(idDetallePeriodoProgramacion);
        return modelMapper.map(response.getResult(), DetallePeriodoProgramacionRequest.class);
    }

    @Override
    public GeneralResponse deleteDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model) {
        return detallePeriodoProgramacionService.deleteDetallePeriodoProgramacion(model);
    }

    @Override
    public IndexViewModelFilter<PoliticasDesarrolloResponse, Integer> getPoliticasDesarrollo(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<PoliticasDesarrolloResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<PoliticasDesarrolloResponse>> designados = politicasDesarrolloService.listarPoliticasDesarrolloByTipo(buscar, SearchPoliticas.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = politicasDesarrolloService.getCantidadByTipo(buscar, SearchPoliticas.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public PoliticasDesarrolloRequest getPoliticasDesarrolloModel(int idAreaEstrategica) {
        return politicasDesarrolloService.getByid(idAreaEstrategica).getResult();
    }

    @Override
    public List<ListView> getAreasEstrategicas(Integer id) {
        return areasEstrategicasService.listAreaEstrategica(id).getResult();
    }

    @Override
    public List<ListView> getPoliticasDesarrollo(int idAreaEstrategica) {
        return politicasDesarrolloService.listPoliticasDesarrolloPorArea(idAreaEstrategica).getResult();
    }

    @Override
    public List<ListView> getPlanesEstrategicosInstitcuinales() {
        return peiService.listPlanEstrategicaInstitucional().getResult();
    }

    @Override
    public List<ListView> getIndicadoresEstrategicos(int idPoliticaDesarrollo) {
        return indicadoresEstrategicosService.listIndicadoresEstrategicosPorPolitica(idPoliticaDesarrollo).getResult();
    }

    @Override
    public Response<PoliticasDesarrolloResponse> savePoliticasDesarrollo(PoliticasDesarrolloRequest model) {
        return politicasDesarrolloService.savePoliticasDesarrollo(model);
    }

    @Override
    public GeneralResponse deletePoliticasDesarrollo(PoliticasDesarrolloRequest model) {
        return politicasDesarrolloService.deletePoliticasDesarrollo(model);
    }

    @Override
    public IndexViewModelFilter<IndicadoresEstrategicosResponse, Integer> getIndicadoresEstrategicos(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<IndicadoresEstrategicosResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<IndicadoresEstrategicosResponse>> designados = indicadoresEstrategicosService.listarIndicadoresEstrategicosByTipo(buscar, SearchIndicadores.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = indicadoresEstrategicosService.getCantidadByTipo(buscar, SearchIndicadores.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }


    @Override
    public IndicadoresEstrategicosRequest getIndicadoresEstrategicosModel(int idIndicadorEstrategico) {
        return indicadoresEstrategicosService.getById(idIndicadorEstrategico).getResult();
    }

    @Override
    public Response<IndicadoresEstrategicosResponse> saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest model) {
        return indicadoresEstrategicosService.saveIndicadoresEstrategicos(model);
    }

    @Override
    public GeneralResponse deleteIndicadoresEstrategicos(IndicadoresEstrategicosRequest model) {
        return indicadoresEstrategicosService.deleteIndicadoresEstrategicos(model);
    }

    @Override
    public IndexViewModelFilter<CatalogoIndicadoresResponse, Integer> getCatalogosIndicadores(ParametrosPaginacionBusquedaRequest<ParametroAreaEstrategicaRequest> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<CatalogoIndicadoresResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar los numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : STR."'%\{model.getBuscar().toUpperCase()}%'";
        ParametroAreaEstrategicaRequest opcion = model.getOption();
        //Listar elementos a mostrar
        Response<List<CatalogoIndicadoresResponse>> designados = catalogoIndicadoresService.listarCatalogoIndicadoresByTipo(buscar, SearchCatalogo.values()[opcion.getOpcion()], cantidadderegistrosporpagina, pagina, opcion.getId());
        if (designados.isSuccess()) {
            Response<Integer> totalRegistros = catalogoIndicadoresService.getCantidadByTipo(buscar, SearchCatalogo.values()[opcion.getOpcion()], opcion.getId());
            filtro.setTotaldeRegistros(totalRegistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public CatalogoIndicadoresRequest getCatalogoIndicadoresModel(int idCatalogoIndicadores) {
        return catalogoIndicadoresService.getByid(idCatalogoIndicadores).getResult();
    }

    @Override
    public Response<Integer> saveCatalogoIndicadores(CatalogoIndicadoresRequest model) {
        return catalogoIndicadoresService.saveCatalogoIndicadores(model);
    }


    @Override
    public GeneralResponse deleteCatalogoIndicadores(Integer id) {
        return catalogoIndicadoresService.deleteCatalogoIndicadores(id);
    }

    @Override
    public List<ListView> getPoliticasDesarrollo() {
        return politicasDesarrolloService.getAllPoliticasDesarrolloA().getResult();
    }

    @Override
    public List<ListView> getAreasEstrategica() {
        return areasEstrategicasService.listAreaEstrategicas().getResult();
    }

    @Override
    public IndexViewModelFilter<CategoriaIndicadorResponse, Integer> getCategoriaIndicador(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<CategoriaIndicadorResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<CategoriaIndicadorResponse>> designados = categoriaIndicadorService.listarCategoriaIndicadorByTipo(buscar, SearchCategoriaIndicador.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = categoriaIndicadorService.getCantidadByTipo(buscar, SearchCategoriaIndicador.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public Response<CategoriaIndicadorResponse> saveCategoriaIndicador(CategoriaIndicadorRequest categoriaIndicador) {
        Response<Integer> result = categoriaIndicadorService.saveCategoriaIndicador(categoriaIndicador);
        return categoriaIndicadorService.getByid(result.getResult());
    }

    @Override
    public CategoriaIndicadorRequest getCategoriaIndicadorModel(int idCategoriaIndicador) {
        return categoriaIndicadorService.getByidCategoriaIndicador(idCategoriaIndicador).getResult();
    }

    @Override
    public GeneralResponse deleteCategoriaIndicador(CategoriaIndicadorRequest model) {
        return categoriaIndicadorService.deleteCategoriaIndicador(model);
    }

    @Override
    public List<ListView> getCategoriaIndicador() {
        return categoriaIndicadorService.getCategorias().getResult();
    }

    @Override
    public List<ListView> getTiposIndicadores() {
        return tipoIndicadorService.getTiposIndicadores().getResult();
    }

    @Override
    public List<ListView> getUnidadesMedidas() {
        return unidadMedidaService.getUnidadMedida().getResult();
    }

    @Override
    public IndexViewModelFilter<TipoIndicadorResponse, Integer> getTipoIndicador(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<TipoIndicadorResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<TipoIndicadorResponse>> designados = tipoIndicadorService.listarTipoIndicadorByTipo(buscar, SearchTipoIndicador.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = tipoIndicadorService.getCantidadByTipo(buscar, SearchTipoIndicador.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public Response<TipoIndicadorResponse> saveTipoIndicador(TipoIndicadorRequest tipoIndicadorRequest) {
        Response<Integer> result = tipoIndicadorService.saveTipoIndicador(tipoIndicadorRequest);
        return tipoIndicadorService.getByid(result.getResult());
    }

    @Override
    public TipoIndicadorRequest getTipoIndicadorModel(int idTipoIndicador) {
        return tipoIndicadorService.getByidTipoIndicador(idTipoIndicador).getResult();
    }

    @Override
    public GeneralResponse deleteTipoIndicador(TipoIndicadorRequest model) {
        return tipoIndicadorService.deleteTipoIndicador(model);
    }

    @Override
    public IndexViewModelFilter<UnidadMedidaResponse, Integer> getUnidadMedida(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<UnidadMedidaResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<UnidadMedidaResponse>> designados = unidadMedidaService.listarUnidadMedidaByTipo(buscar, SearchUnidadMedida.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = unidadMedidaService.getCantidadByTipo(buscar, SearchUnidadMedida.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public Response<UnidadMedidaResponse> saveUnidadMedida(UnidadMedidaRequest unidadMedidaRequest) {
        Response<Integer> result = unidadMedidaService.saveUnidadMedida(unidadMedidaRequest);
        return unidadMedidaService.getByid(result.getResult());
    }

    @Override
    public UnidadMedidaRequest getUnidadMedidaModel(int idUnidadMedida) {
        return unidadMedidaService.getByidUnidadMedia(idUnidadMedida).getResult();
    }

    @Override
    public GeneralResponse deleteUnidadMedida(UnidadMedidaRequest model) {
        return unidadMedidaService.deleteUnidadMedida(model);
    }

    @Override
    public IndexViewModelFilter<AperturasProgramaticasResponse, Integer> getAperturasProgramaticas(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<AperturasProgramaticasResponse, Integer> filtro = new IndexViewModelFilter<>();

        //Lista para mostrar el numero de elementos
        List<SelectListItemDto> moxstrarelementos = RequestUtils.getCantidadDeElementos();

        //Cantidad a mostrar por pagina
        int cantidadderegistrosporpagina = model.getMostrar();

        //Mostrar la pagina actual
        int pagina = (model.getPagina() - 1) * cantidadderegistrosporpagina;

        //Parametro de busqueda en elementos
        String buscar = model.getBuscar() == null ? "'%%'" : "'%" + model.getBuscar().toUpperCase() + "%'";
        Object opcion = model.getOption();
        //Lista elementos a mostrar
        Response<List<AperturasProgramaticasResponse>> designados = aperturasProgramaticasService.listarAperturasProgramaticasByTipo(buscar, SearchAperturas.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = aperturasProgramaticasService.getCantidadByTipo(buscar, SearchAperturas.values()[Integer.parseInt(opcion.toString())]);
            filtro.setTotaldeRegistros(totalregistros.getResult());
        } else {
            filtro.setTotaldeRegistros(0);
        }
        filtro.setLista(designados.getResult());
        filtro.setPaginaActual(model.getPagina());
        filtro.setRegistrosporPagina(cantidadderegistrosporpagina);
        filtro.setMostrarElementos(moxstrarelementos);
        filtro.setMostrar(cantidadderegistrosporpagina);
        filtro.setOpcion(opcion.toString());
        return filtro;
    }

    @Override
    public Response<AperturasProgramaticasResponse> saveAperturasProgramaticas(AperturasProgramaticasRequest aperturasProgramaticas) {
        Response<Integer> result = aperturasProgramaticasService.saveAperturasProgramaticas(aperturasProgramaticas);
        return aperturasProgramaticasService.getByid(result.getResult());
    }

    @Override
    public AperturasProgramaticasRequest getAperturasProgramaticasModel(int idAperturasProgramatica) {
        return aperturasProgramaticasService.getByidAperturasProgramaticas(idAperturasProgramatica).getResult();
    }

    @Override
    public GeneralResponse deleteAperturasProgramaticas(AperturasProgramaticasRequest model) {
        return aperturasProgramaticasService.deleteAperturasProgramaticas(model);
    }

    @Override
    public List<ListView> getListaAperturasProgramaticas() {
        return aperturasProgramaticasService.getListaAperturasProgramaticas().getResult();
    }

    @Override
    public ListView getCatalogoIndicador(Integer id) {
        var response = catalogoIndicadoresService.itemCatalogoIndicador(id).getResult();
        return new ListView(response.getId_catalogo_indicador_pei().toString(), response.getDenominacion_indicador());
    }
}
