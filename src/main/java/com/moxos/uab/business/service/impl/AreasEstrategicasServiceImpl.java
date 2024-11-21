package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IAreasEstrategicasService;
import com.moxos.uab.common.enums.SearchAreas;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.AreaEstrategica;
import com.moxos.uab.persistence.die.AreaEstrategicaDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AreasEstrategicasServiceImpl implements IAreasEstrategicasService {
    private ModelMapper modelMapper;
    private final AreaEstrategicaDao areaEstrategicaDao;


    public AreasEstrategicasServiceImpl(AreaEstrategicaDao areaEstrategicaDao, ModelMapper modelMapper) {
        this.areaEstrategicaDao = areaEstrategicaDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<Integer> saveAreaEstrategica(AreasEstrategicasRequest areaEstrategica) {
        try {
            Integer result = areaEstrategicaDao.saveAreaEstrategica(modelMapper.map(areaEstrategica, AreaEstrategica.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
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
    public Response<List<ListView>> listAreaEstrategica(String gestion) {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : areaEstrategicaDao.getAllAreaEstrategicaPorGestion(gestion))
                listViews.add(new ListView(String.valueOf(item.getId_area_estrategica()), item.getArea_estrategica()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }



    @Override
    public Response<AreaEstrategicaResponse> getByid(int id_area_estrategica) {
        try {
            AreaEstrategicaResponse areaEstrategicas = modelMapper.map(areaEstrategicaDao.getByid(id_area_estrategica), AreaEstrategicaResponse.class);
            return new Response<>(true, "", areaEstrategicas);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<AreaEstrategicaResponse>> listarAreaEstrategicaByTipo(String buscar, SearchAreas searchAreas, int pagina, int nroPagina) {
        AreaEstrategica areaEstrategica = new AreaEstrategica();
        areaEstrategica.setPagina(pagina);
        areaEstrategica.setNro_pagina(nroPagina);
        areaEstrategica.setBuscar(buscar);
        try {
            List<AreaEstrategicaResponse> areaEstrategicas = new ArrayList<>();
            switch (searchAreas) {
                case DESCRIPCION:
                    areaEstrategicas = areaEstrategicaDao.getAreasEstrategicasByArea(areaEstrategica).stream().map(p -> modelMapper.map(p, AreaEstrategicaResponse.class)).toList();
                    break;
                case GESTION:
                    areaEstrategicas = areaEstrategicaDao.getAreasEstrategicasByGestion(areaEstrategica).stream().map(p -> modelMapper.map(p, AreaEstrategicaResponse.class)).toList();
                    break;
                case CODIGO:
                    areaEstrategicas = areaEstrategicaDao.getAreasEstrategicasByCodigo(areaEstrategica).stream().map(p -> modelMapper.map(p, AreaEstrategicaResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", areaEstrategicas);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchAreas searchAreas) {
        AreaEstrategica areaEstrategica = new AreaEstrategica();
        areaEstrategica.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (searchAreas) {
                case DESCRIPCION:
                    cantidad = areaEstrategicaDao.getCantidadAreasEstrategicasByArea(areaEstrategica);
                    break;
                case GESTION:
                    cantidad = areaEstrategicaDao.getCantidadAreasEstrategicasByGestion(areaEstrategica);
                    break;
                case CODIGO:
                    cantidad = areaEstrategicaDao.getCantidadAreasEstrategicasByCodigo(areaEstrategica);
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", cantidad);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<AreasEstrategicasRequest> getByidAreasEstrategicas(int idAreaEstrategica) {
        try {
            AreasEstrategicasRequest areaEstrategicas = modelMapper.map(areaEstrategicaDao.getByid(idAreaEstrategica), AreasEstrategicasRequest.class);
            return new Response<>(true, "", areaEstrategicas);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

}
