package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IResultadosService;
import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosDetalleResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.Resultados;
import com.moxos.uab.persistence.die.ResultadosDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ResultadosServiceImpl implements IResultadosService {
    private ModelMapper modelMapper;
    private ResultadosDao resultadosDao;

    public ResultadosServiceImpl(ModelMapper modelMapper, ResultadosDao resultadosDao) {
        this.modelMapper = modelMapper;
        this.resultadosDao = resultadosDao;
    }

    @Override
    public Response<Integer> saveResultados(ResultadosRequest model) {
        try {
            Integer result = resultadosDao.saveResultados(modelMapper.map(model, Resultados.class));
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), -1);
        }
    }

    @Override
    public Response<ResultadosRequest> getResultadoByid(Integer id) {
        try {
            ResultadosRequest result = modelMapper.map(resultadosDao.getByid(id), ResultadosRequest.class);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse deleteResultados(ResultadosRequest model) {
        try {
            resultadosDao.deleteResultado(model.getId_resultados());
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<List<ListView>> getListarResultadosPorGestionFormulario(Integer idPeriodoGestion, Integer idFormulario) {
        try {
            List<ListView> resultadosList = new ArrayList<>();
            Resultados resultados = new Resultados();
            resultados.setId_detalle_periodos_programacion(idPeriodoGestion);
            resultados.setId_formulario(idFormulario);
            var response = resultadosDao.getListaResultadoPorGestionFormulario(resultados);
            for (var item : response) {
                resultadosList.add(new ListView(item.getId_resultados().toString(), item.getDescripcion()));
            }
            return new Response<>(true, "", resultadosList);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<ResultadosDetalleResponse> getResultadoDetalleByid(Integer idPeriodoGestion, Integer idResultado) {
        try {
            Resultados resultados = new Resultados();
            resultados.setId_detalle_periodos_programacion(idPeriodoGestion);
            resultados.setId_resultados(idResultado);
            var response = modelMapper.map(resultadosDao.getResultadosDetallePorPeriodo(resultados), ResultadosDetalleResponse.class);
            return new Response<>(true, "", response);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}

