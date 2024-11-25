package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.business.service.*;
import com.moxos.uab.business.service.impl.DetallePeriodoProgramacionServiceImpl;
import com.moxos.uab.common.enums.*;
import com.moxos.uab.common.util.RequestUtils;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.ParametroPeiRequest;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.dto.response.configuration.ConfigurationResponse;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
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

    public PoliticasIndicadoresAreasFacadeImpl(IAreasEstrategicasService areasEstrategicasService, IPoliticasDesarrolloService politicasDesarrolloService, IConfigurationService configurationService, IIndicadoresEstrategicosService indicadoresEstrategicosService, ICatalogoIndicadoresService catalogoIndicadoresService, IPeiService peiService, IDetallePeriodoProgramacionService detallePeriodoProgramacionService) {
        this.areasEstrategicasService = areasEstrategicasService;
        this.politicasDesarrolloService = politicasDesarrolloService;
        this.configurationService = configurationService;
        this.indicadoresEstrategicosService = indicadoresEstrategicosService;
        this.catalogoIndicadoresService = catalogoIndicadoresService;
        this.peiService = peiService;
        this.detallePeriodoProgramacionService = detallePeriodoProgramacionService;
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
    public AreasEstrategicasRequest getAreaEstrategicasModel(int idAreaEstrategica) {
        return areasEstrategicasService.getByidAreasEstrategicas(idAreaEstrategica).getResult();
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
        return null;
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
    public List<ListView> getAreasEstrategicas() {
        ConfigurationResponse config = configurationService.getConfiguracionActual();
        return areasEstrategicasService.listAreaEstrategica(config.getGestion()).getResult();
    }

    @Override
    public List<ListView> getPoliticasDesarrollo(int idAreaEstrategica) {
        return politicasDesarrolloService.listPoliticasDesarrolloPorArea(idAreaEstrategica).getResult();
    }

    @Override
    public List<ListView> getIndicadoresEstrategicos(int idPoliticaDesarrollo) {
        return indicadoresEstrategicosService.listIndicadoresEstrategicosPorPolitica(idPoliticaDesarrollo).getResult();
    }

    @Override
    public List<ListView> getCatalogoIndicadores(int idIndicadorEstrategico) {
        return List.of();
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
    public IndexViewModelFilter<CatalogoIndicadoresResponse, Integer> getCatalogosIndicadores(ParametrosPaginacionBusquedaRequest<Integer> model) {
        //Clase generica para la paginacion
        IndexViewModelFilter<CatalogoIndicadoresResponse, Integer> filtro = new IndexViewModelFilter<>();

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
        Response<List<CatalogoIndicadoresResponse>> designados = catalogoIndicadoresService.listarCatalogoIndicadoresByTipo(buscar, SearchCatalogo.values()[Integer.parseInt(opcion.toString())], cantidadderegistrosporpagina, pagina);
        if (designados.isSuccess()) {
            Response<Integer> totalregistros = catalogoIndicadoresService.getCantidadByTipo(buscar, SearchCatalogo.values()[Integer.parseInt(opcion.toString())]);
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
    public CatalogoIndicadoresRequest getCatalogoIndicadoresModel(int idCatalogoIndicadores) {
        return catalogoIndicadoresService.getByid(idCatalogoIndicadores).getResult();
    }

    @Override
    public Response<CatalogoIndicadoresResponse> saveCatalogoIndicadores(CatalogoIndicadoresRequest model) {
        return catalogoIndicadoresService.saveCatalogoIndicadores(model);
    }


    @Override
    public GeneralResponse deleteCatalogoIndicadores(CatalogoIndicadoresRequest model) {
        return catalogoIndicadoresService.deleteCatalogoIndicadores(model);
    }

    @Override
    public List<ListView> getPoliticasDesarrollo() {
        return politicasDesarrolloService.getAllPoliticasDesarrolloA().getResult();
    }

}
