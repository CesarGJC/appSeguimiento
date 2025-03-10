package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IResultadosGestionService;
import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.request.resultadosgestion.ResultadosGestionRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionDetalleResponse;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionResponse;
import com.moxos.uab.domain.entity.die.ResultadosGestion;
import com.moxos.uab.persistence.die.ResultadosDao;
import com.moxos.uab.persistence.die.ResultadosGestionDao;
import org.eclipse.tags.shaded.org.apache.xalan.templates.ElemWhen;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultadosGestionServiceImpl implements IResultadosGestionService {
    private final ModelMapper modelMapper;
    private final ResultadosGestionDao resultadosGestionDao;
    private final ResultadosDao resultadosDao;

    public ResultadosGestionServiceImpl(ModelMapper modelMapper, ResultadosGestionDao resultadosGestionDao, ResultadosDao resultadosDao) {
        this.modelMapper = modelMapper;
        this.resultadosGestionDao = resultadosGestionDao;
        this.resultadosDao = resultadosDao;
    }

    @Override
    public Response<Integer> saveResultadosGestion(ResultadosGestionRequest resultadosGestionRequest) {
        try {
            Integer result = resultadosGestionDao.saveResultadosGestion(modelMapper.map(resultadosGestionRequest, ResultadosGestion.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse saveResultadosGestiones(List<ResultadosGestionRequest> resultadosGestionRequests) {
        try {
            for (var resultadosGestionRequest : resultadosGestionRequests) {
                resultadosGestionDao.saveResultadosGestion(modelMapper.map(resultadosGestionRequest, ResultadosGestion.class));
            }
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }

    }

    @Override
    public GeneralResponse deleteResultadosGestion(ResultadosGestionRequest resultadosGestionRequest) {
        try {
            resultadosGestionDao.deleteResultadoGestion(modelMapper.map(resultadosGestionRequest, ResultadosGestion.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<ResultadosGestionResponse> getById(int id_resultados_gestion) {
        try {
            ResultadosGestionResponse resultadosGestionResponse = modelMapper.map(resultadosGestionDao.getByid(id_resultados_gestion), ResultadosGestionResponse.class);
            return new Response<>(true, "", resultadosGestionResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<ResultadosRequest> getResultadoGestionByid(Integer id) {
        return null;
    }

    @Override
    public Response<List<ResultadosGestionResponse>> getResultadosGestion(int Resultados) {
        try {
            List<ResultadosGestionResponse> listViews = new ArrayList<>();
            for (var item : resultadosGestionDao.getResultadosGestion(Resultados))
                listViews.add(new ResultadosGestionResponse(item.getId_resultados_gestion(), item.getDescripcion()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<DetallePeriodoProgramacionResponse>> getPeriodosProgramacion(int Periodo) {
        try {
            List<DetallePeriodoProgramacionResponse> listViews = new ArrayList<>();
            for (var item : resultadosGestionDao.getPeriodosProgramacion(Periodo))
                listViews.add(new DetallePeriodoProgramacionResponse(item.getId_detalle_periodos_programacion(), item.getDescripcion()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<ResultadosGestionDetalleResponse> getDetalleProgramacionPorResultados(ResultadosGestionRequest model) {
        try {
            var detalleProgramacion = resultadosGestionDao.getDetalleProgramacionPorResultados(modelMapper.map(model, ResultadosGestion.class));
            return new Response<>(detalleProgramacion != null, detalleProgramacion == null ? "No existe el elemento" : "", detalleProgramacion != null ? modelMapper.map(detalleProgramacion, ResultadosGestionDetalleResponse.class) : null);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
