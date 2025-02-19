package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IAccionesEstrategicasService;
import com.moxos.uab.domain.dto.request.accionestrategica.AccionEstrategicaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaDetalleResponse;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;
import com.moxos.uab.domain.entity.die.AccionEstrategica;
import com.moxos.uab.persistence.die.AccionEstrategicaDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccionesEstrategicasServiceImpl implements IAccionesEstrategicasService {
    private final ModelMapper modelMapper;
    private final AccionEstrategicaDao accionEstrategicaDao;

    public AccionesEstrategicasServiceImpl(ModelMapper modelMapper, AccionEstrategicaDao accionEstrategicaDao) {
        this.modelMapper = modelMapper;
        this.accionEstrategicaDao = accionEstrategicaDao;
    }

    @Override
    public Response<Integer> saveAccionEstrategica(AccionEstrategicaRequest accionEstrategicaRequest) {
        try {
            var value = modelMapper.map(accionEstrategicaRequest, AccionEstrategica.class);
            Integer result = accionEstrategicaDao.saveAccionEstrategica(value);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteAccionEstrategica(AccionEstrategicaRequest accionEstrategicaRequest) {
        try {
            accionEstrategicaDao.deleteAccionEstrategica(modelMapper.map(accionEstrategicaRequest, AccionEstrategica.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<AccionEstrategicaResponse> getByid(int id_acciones_estrategica) {
        try {
            AccionEstrategicaResponse accionEstrategicaResponse = modelMapper.map(accionEstrategicaDao.getByid(id_acciones_estrategica), AccionEstrategicaResponse.class);
            return new Response<>(true, "", accionEstrategicaResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<AccionEstrategicaDetalleResponse> getDetalleByid(int id_acciones_estrategica) {
        try {
            AccionEstrategicaDetalleResponse accionEstrategicaResponse = modelMapper.map(accionEstrategicaDao.getDetalleAccionesEstrategicas(id_acciones_estrategica), AccionEstrategicaDetalleResponse.class);
            return new Response<>(true, "", accionEstrategicaResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<AccionEstrategicaResponse>> getAcciones(int Accion) {
        try {
            List<AccionEstrategicaResponse> listViews = new ArrayList<>();
            for (var item : accionEstrategicaDao.getAcciones(Accion))
                listViews.add(new AccionEstrategicaResponse(item.getId_acciones_estrategica(), item.getAcciones_estrategica()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
