package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IAreasEstrategicasService;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.AreaEstrategica;
import com.moxos.uab.persistence.die.AreaEstrategicaDao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AreasEstrategicasServiceImpl implements IAreasEstrategicasService {
    private ModelMapper modelMapper;
    private final AreaEstrategicaDao areaEstrategicaDao;

    public AreasEstrategicasServiceImpl(AreaEstrategicaDao areaEstrategicaDao, ModelMapper modelMapper) {
        this.areaEstrategicaDao = areaEstrategicaDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public GeneralResponse saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica) {
        try {
            areaEstrategicaDao.saveAreaEstrategica(modelMapper.map(areaEstrategica, AreaEstrategica.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public GeneralResponse updateAreaEstrategica(AreasEstrategicasRequest areaEstrategica) {
        try {
            areaEstrategicaDao.updateAreaEstrategica(modelMapper.map(areaEstrategica, AreaEstrategica.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public GeneralResponse deleteAreaEstrategica(AreasEstrategicasRequest areaEstrategica) {
        try {
            areaEstrategicaDao.deleteAreaEstrategica(modelMapper.map(areaEstrategica, AreaEstrategica.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<List<AreaEstrategicaResponse>> selectAreaEstrategica() {
        try {
            List<AreaEstrategicaResponse> areaEstrategicas = modelMapper.map(areaEstrategicaDao.getAllAreaEstrategica(), List.class);
            return new Response<>(true, "", areaEstrategicas);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> listAreaEstrategica() {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : areaEstrategicaDao.getAllAreaEstrategica())
                listViews.add(new ListView(String.valueOf(item.getId_area_estrategica()), item.getArea_estrategica()));

            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<AreasEstrategicasRequest> getByid(int id_area_estrategica) {
        try {
            AreasEstrategicasRequest areaEstrategicas = modelMapper.map(areaEstrategicaDao.getByid(id_area_estrategica), AreasEstrategicasRequest.class);
            return new Response<>(true, "", areaEstrategicas);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
