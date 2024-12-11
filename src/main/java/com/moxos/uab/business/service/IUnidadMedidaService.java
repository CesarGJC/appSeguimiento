package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchTipoIndicador;
import com.moxos.uab.common.enums.SearchUnidadMedida;
import com.moxos.uab.domain.dto.request.unidadmedida.UnidadMedidaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.unidadmedida.UnidadMedidaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IUnidadMedidaService {
    Response<Integer> saveUnidadMedida(UnidadMedidaRequest unidadMedidaRequest);

    GeneralResponse deleteUnidadMedida(UnidadMedidaRequest unidadMedidaRequest);

    Response<UnidadMedidaResponse> getByid(int id_unidad_medida);

    Response<List<UnidadMedidaResponse>> listarUnidadMedidaByTipo(String buscar, SearchUnidadMedida searchUnidadMedida, int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchUnidadMedida searchUnidadMedida);

    Response<UnidadMedidaRequest> getByidUnidadMedia(int id_unidad_medida);

    Response<List<ListView>> getUnidadMedida();

}
