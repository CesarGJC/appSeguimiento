package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchCategoriaIndicador;
import com.moxos.uab.domain.dto.request.categoriaindicador.CategoriaIndicadorRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.categoriaindicador.CategoriaIndicadorResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface ICategoriaIndicadorService {
    Response<Integer> saveCategoriaIndicador(CategoriaIndicadorRequest categoriaIndicador);

    GeneralResponse deleteCategoriaIndicador(CategoriaIndicadorRequest categoriaIndicador);

    Response<CategoriaIndicadorResponse> getByid(int id_categoria);

    Response<List<CategoriaIndicadorResponse>> listarCategoriaIndicadorByTipo(String buscar, SearchCategoriaIndicador searchCategoriaIndicador, int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchCategoriaIndicador searchCategoriaIndicador);

    Response<CategoriaIndicadorRequest> getByidCategoriaIndicador(int id_categoria);

    Response<List<ListView>> getCategorias();
}
