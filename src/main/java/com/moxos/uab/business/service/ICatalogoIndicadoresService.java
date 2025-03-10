package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchCatalogo;
import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface ICatalogoIndicadoresService {

    Response<Integer> saveCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores);

    GeneralResponse deleteCatalogoIndicadores(Integer id);

    Response<List<CatalogoIndicadoresResponse>> selectCatalogoIndicadores();

    Response<List<ListView>> listCatalogoIndicadores();

    Response<CatalogoIndicadoresRequest> getByid(int id_catalogo_indicador);

    Response<List<CatalogoIndicadoresResponse>> listarCatalogoIndicadoresByTipo(String buscar, SearchCatalogo SearchIndicadores, int pagina, int nroPagina, int id);

    Response<Integer> getCantidadByTipo(String buscar, SearchCatalogo SearchIndicadores, int id);

    Response<List<ListView>> listCatalogoIndicadoresPorArea(int idAreaEstrategica);

    Response<CatalogoIndicadoresRequest> itemCatalogoIndicador(int idCatalogoIndicador);
}
