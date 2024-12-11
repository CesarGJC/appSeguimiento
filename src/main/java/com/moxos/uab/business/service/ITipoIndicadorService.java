package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchTipoIndicador;
import com.moxos.uab.domain.dto.request.tipoindicador.TipoIndicadorRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.tipoindicador.TipoIndicadorResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface ITipoIndicadorService {
    Response<Integer> saveTipoIndicador(TipoIndicadorRequest tipoIndicadorRequest);

    GeneralResponse deleteTipoIndicador(TipoIndicadorRequest tipoIndicadorRequest);

    Response<TipoIndicadorResponse> getByid(int id_tipo_indicador);

    Response<List<TipoIndicadorResponse>> listarTipoIndicadorByTipo(String buscar, SearchTipoIndicador searchTipoIndicador, int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchTipoIndicador searchTipoIndicador);

    Response<TipoIndicadorRequest> getByidTipoIndicador(int id_tipo_indicador);

    Response<List<ListView>> getTiposIndicadores();

}
