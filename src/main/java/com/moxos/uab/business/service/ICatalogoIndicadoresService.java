package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface ICatalogoIndicadoresService {
    GeneralResponse saveCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores);

    GeneralResponse updateCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores);

    GeneralResponse deleteCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores);

    Response<List<CatalogoIndicadoresResponse>> selectCatalogoIndicadores();

    Response<List<ListView>> listCatalogoIndicadores();

    Response<CatalogoIndicadoresRequest> getByid(int id_catalogo_indicador);
}
