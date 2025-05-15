package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosDetalleResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.ArrayList;
import java.util.List;

public interface IResultadosService {
    Response<Integer> saveResultados(ResultadosRequest model);

    Response<ResultadosRequest> getResultadoByid(Integer id);

    GeneralResponse deleteResultados(ResultadosRequest model);

    Response<List<ListView>> getListarResultadosPorGestionFormulario(Integer idPeriodoGestion, Integer idFormulario);

    Response<ResultadosDetalleResponse> getResultadoDetalleByid(Integer idPeriodoGestion, Integer idResultado);

    Response<ResultadosResponse> getResultadoDescripcion(Integer idResultado);

    Response<List<ListView>> listarResultados(Integer id);

}
