package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IAreasEstrategicasService {
    GeneralResponse saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    GeneralResponse updateAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    GeneralResponse deleteAreaEstrategica(AreasEstrategicasRequest areaEstrategica);

    Response<List<AreaEstrategicaResponse>> selectAreaEstrategica();

    Response<List<ListView>> listAreaEstrategica();

    Response<AreasEstrategicasRequest> getByid(int id_area_estrategica);

}
