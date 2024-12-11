package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchAreas;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreasEstrategicasDeleteResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IAreasEstrategicasService {
    Response<Integer> saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    GeneralResponse deleteAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    Response<List<AreaEstrategicaResponse>> selectAreaEstrategica();

    Response<List<ListView>> listAreaEstrategica(Integer id);

    Response<AreaEstrategicaResponse> getByid(int id_area_estrategica);

    Response<List<AreaEstrategicaResponse>> listarAreaEstrategicaByTipo(String buscar, SearchAreas searchAreas, int pagina, int nroPagina);

    Response<Integer> getCantidadByTipo(String buscar, SearchAreas searchAreas);

    Response<AreasEstrategicasDeleteResponse> getByidAreasEstrategicas(int idAreaEstrategica);

    Response<AreasEstrategicasRequest> getByidAreasEstrategicasDetalle(int idAreaEstrategica);
}
