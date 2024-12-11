package com.moxos.uab.business.service;

import com.moxos.uab.common.enums.SearchDetallePeriodo;
import com.moxos.uab.common.enums.SearchPoliticas;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.PeriodosProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IDetallePeriodoProgramacionService {
    Response<DetallePeriodoProgramacionResponse> saveDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model);

    GeneralResponse deleteDetallePeriodoProgramacion(DetallePeriodoProgramacionRequest model);

    Response<List<ListView>> listDetallePeriodoProgramacionPorPei(Integer idPlanPei);

    Response<DetallePeriodoProgramacionRequest> getByid(int id_detalle_periodos_programacion);

    Response<List<DetallePeriodoProgramacionResponse>> listarDetallePeriodoProgramacionByTipo(String buscar, SearchDetallePeriodo searchPei, int pagina, int nroPagina, int id);

    Response<Integer> getCantidadByTipo(String buscar, SearchDetallePeriodo searchPei, Integer id);

    Response<List<PeriodosProgramacionResponse>> getPeriodosPlan(int idPlan);
}
