package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.PeriodosProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioResultadosResponse;
import com.moxos.uab.domain.dto.response.objetivosestrategicos.ObjetivosEstrategicosResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;

import java.util.List;

public interface IObjetivosEstrategicosService {
    Response<Integer> saveObjetivosEstrategicos(ObjetivosEstrategicosRequest objetivoEstrategicos);

    GeneralResponse deleteObjetivosEstrategicos(ObjetivosEstrategicosRequest objetivoEstrategicos);

    Response<ObjetivosEstrategicosResponse> getByid(int id_objetivos_estrategicos);

    Response<List<FormularioResultadosResponse>> getObjetivosEstrategicos(FormularioResultadosResponse FormularioResultadosResponse);
}
