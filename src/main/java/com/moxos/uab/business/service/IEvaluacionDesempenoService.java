package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.evaluaciondesempeno.EvaluacionDesempenoRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.evaluaciondesempeno.EvaluacionDesempenoResponse;

import java.util.List;

public interface IEvaluacionDesempenoService {
    Response<Integer> saveEvaluacionDesempeno(EvaluacionDesempenoRequest evaluacionDesempenoRequest);

    GeneralResponse deleteEvaluacionDesempeno(EvaluacionDesempenoRequest evaluacionDesempenoRequest);

    Response<EvaluacionDesempenoResponse> getByid(int id_evaluacion_desempeno);

    Response<List<EvaluacionDesempenoResponse>> getEvaluacionDesempeno(int id_evaluacion_desempeno );
}
