package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IAperturasProgramaticasService;
import com.moxos.uab.common.enums.SearchAperturas;
import com.moxos.uab.domain.dto.request.aperturasprogramaticas.AperturasProgramaticasResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.aperturasprogramaticas.AperturasProgramaticasRequest;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.AperturasProgramaticas;
import com.moxos.uab.persistence.die.AperturasProgramaticasDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AperturasProgramaticasServiceImpl implements IAperturasProgramaticasService {
    private final ModelMapper modelMapper;
    private final AperturasProgramaticasDao aperturasProgramaticasDao;

    public AperturasProgramaticasServiceImpl(ModelMapper modelMapper, AperturasProgramaticasDao aperturasEstrategicasDao, AperturasProgramaticasDao aperturasProgramaticasDao) {
        this.modelMapper = modelMapper;
        this.aperturasProgramaticasDao = aperturasProgramaticasDao;
    }

    @Override
    public Response<Integer> saveAperturasProgramaticas(AperturasProgramaticasRequest aperturasProgramaticasRequest) {
        try {
            Integer result = aperturasProgramaticasDao.saveAperturasProgramaticas(modelMapper.map(aperturasProgramaticasRequest, AperturasProgramaticas.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteAperturasProgramaticas(AperturasProgramaticasRequest aperturasEstrategicasRequest) {
        try {
            aperturasProgramaticasDao.deleteAperturasProgramaticas(modelMapper.map(aperturasEstrategicasRequest, AperturasProgramaticas.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<AperturasProgramaticasResponse> getByid(int id_apertura_programatica) {
        try {
            AperturasProgramaticasResponse aperturasProgramaticasResponse = modelMapper.map(aperturasProgramaticasDao.getByid(id_apertura_programatica), AperturasProgramaticasResponse.class);
            return new Response<>(true, "", aperturasProgramaticasResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<AperturasProgramaticasResponse>> listarAperturasProgramaticasByTipo(String buscar, SearchAperturas searchAperturas, int pagina, int nroPagina) {
        AperturasProgramaticas aperturasProgramaticas = new AperturasProgramaticas();
        aperturasProgramaticas.setPagina(pagina);
        aperturasProgramaticas.setNro_pagina(nroPagina);
        aperturasProgramaticas.setBuscar(buscar);
        try {
            List<AperturasProgramaticasResponse> aperturas = new ArrayList<>();
            switch (searchAperturas) {
                case DESCRIPCION:
                    aperturas = aperturasProgramaticasDao.getAperturasProgramaticasByAperturasProgramaticas(aperturasProgramaticas).stream().map(p -> modelMapper.map(p, AperturasProgramaticasResponse.class)).toList();
                    break;
                case CODIGO:
                    aperturas = aperturasProgramaticasDao.getAperturasProgramaticasByCodigo(aperturasProgramaticas).stream().map(p -> modelMapper.map(p, AperturasProgramaticasResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", aperturas);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchAperturas searchAperturas) {
        AperturasProgramaticas aperturasProgramaticas = new AperturasProgramaticas();
        aperturasProgramaticas.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (searchAperturas) {
                case DESCRIPCION:
                    cantidad = aperturasProgramaticasDao.getCantidadAperturasProgramaticasByAperturasProgramaticas(aperturasProgramaticas);
                    break;
                case CODIGO:
                    cantidad = aperturasProgramaticasDao.getCantidadAperturasProgramaticasByCodigo(aperturasProgramaticas);
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
    public Response<AperturasProgramaticasRequest> getByidAperturasProgramaticas(int id_apertura_programatica) {
        try {
            AperturasProgramaticasRequest aperturasProgramaticasRequest = modelMapper.map(aperturasProgramaticasDao.getByid(id_apertura_programatica), AperturasProgramaticasRequest.class);
            return new Response<>(true, "", aperturasProgramaticasRequest);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> listAperturasProgramaticas() {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : aperturasProgramaticasDao.getAllAperturasProgramaticas())
                listViews.add(new ListView(String.valueOf(item.getId_apertura_programatica()), item.getApertura_programatica()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
    @Override
    public Response<List<ListView>> getListaAperturasProgramaticas() {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : aperturasProgramaticasDao.getAllAperturasProgramaticas())
                listViews.add(new ListView(String.valueOf(item.getId_apertura_programatica()), String.format("%s(%s)", item.getApertura_programatica(), item.getCodigo())));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
