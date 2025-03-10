package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.accionestrategica.AccionEstrategicaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaDetalleResponse;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;

import java.util.List;

public interface IAccionesEstrategicasService {
    Response<Integer> saveAccionEstrategica(AccionEstrategicaRequest accionEstrategicaRequest);

    GeneralResponse deleteAccionEstrategica(AccionEstrategicaRequest accionEstrategicaRequest);

    Response<AccionEstrategicaResponse> getByid(int id_acciones_estrategica);

    Response<List<AccionEstrategicaResponse>> getAcciones(int Accion);

    Response<AccionEstrategicaDetalleResponse> getDetalleByid(int id_acciones_estrategica);
}
