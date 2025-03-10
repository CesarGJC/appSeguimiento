package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.request.resultadosgestion.ResultadosGestionRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionDetalleResponse;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionResponse;

import java.util.List;

public interface IResultadosGestionService {
    Response<Integer> saveResultadosGestion(ResultadosGestionRequest resultadosGestionRequest);

    GeneralResponse saveResultadosGestiones(List<ResultadosGestionRequest> resultadosGestionRequests);

    GeneralResponse deleteResultadosGestion(ResultadosGestionRequest resultadosGestionRequest);

    Response<ResultadosGestionResponse> getById(int id_resultados_gestion);

    Response<ResultadosRequest> getResultadoGestionByid(Integer id);

    Response<List<ResultadosGestionResponse>> getResultadosGestion(int Resultados);

    Response<List<DetallePeriodoProgramacionResponse>> getPeriodosProgramacion(int Periodo);

    Response<ResultadosGestionDetalleResponse> getDetalleProgramacionPorResultados(ResultadosGestionRequest resultadosGestionRequest);
}
