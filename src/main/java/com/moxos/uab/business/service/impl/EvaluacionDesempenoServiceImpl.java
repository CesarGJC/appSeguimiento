package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IEvaluacionDesempenoService;
import com.moxos.uab.domain.dto.request.evaluaciondesempeno.EvaluacionDesempenoRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.evaluaciondesempeno.EvaluacionDesempenoResponse;
import com.moxos.uab.domain.entity.die.EvaluacionDesempeno;
import com.moxos.uab.persistence.die.EvaluacionDesempenoDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluacionDesempenoServiceImpl implements IEvaluacionDesempenoService {
    private final ModelMapper modelMapper;
    private final EvaluacionDesempenoDao evaluacionDesempenoDao;

    public EvaluacionDesempenoServiceImpl(ModelMapper modelMapper, EvaluacionDesempenoDao evaluacionDesempenoDao) {
        this.modelMapper = modelMapper;
        this.evaluacionDesempenoDao = evaluacionDesempenoDao;
    }


    @Override
    public Response<Integer> saveEvaluacionDesempeno(EvaluacionDesempenoRequest evaluacionDesempenoRequest) {
        try {
            Integer result = evaluacionDesempenoDao.saveEvaluacionDesempeno(modelMapper.map(evaluacionDesempenoRequest, EvaluacionDesempeno.class));
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteEvaluacionDesempeno(EvaluacionDesempenoRequest evaluacionDesempenoRequest) {
        try {
            evaluacionDesempenoDao.deleteEvaluacionDesempeno(modelMapper.map(evaluacionDesempenoRequest, EvaluacionDesempeno.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<EvaluacionDesempenoResponse> getByid(int id_evaluacion_desempeno) {
        try {
            EvaluacionDesempenoResponse evaluacionDesempenoResponse = modelMapper.map(evaluacionDesempenoDao.getByid(id_evaluacion_desempeno), EvaluacionDesempenoResponse.class);
            return new Response<>(true, "", evaluacionDesempenoResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<EvaluacionDesempenoResponse>> getEvaluacionDesempeno(int id_evaluacion_desempeno) {
        try {
            List<EvaluacionDesempenoResponse> listViews = new ArrayList<>();
            for (var item : evaluacionDesempenoDao.getEvaluacionDesempeno(id_evaluacion_desempeno))
                listViews.add(new EvaluacionDesempenoResponse(item.getId_evaluacion_desempeno(), item.getDescripcion()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
