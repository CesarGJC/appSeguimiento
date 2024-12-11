package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.ITipoIndicadorService;
import com.moxos.uab.common.enums.SearchTipoIndicador;
import com.moxos.uab.domain.dto.request.tipoindicador.TipoIndicadorRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.tipoindicador.TipoIndicadorResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.TipoIndicador;
import com.moxos.uab.persistence.die.TipoIndicadorDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoIndicadorServiceImpl implements ITipoIndicadorService {
    private ModelMapper modelMapper;
    private final TipoIndicadorDao tipoIndicadorDao;

    public TipoIndicadorServiceImpl(TipoIndicadorDao tipoIndicadorDao) {
        this.tipoIndicadorDao = tipoIndicadorDao;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Response<Integer> saveTipoIndicador(TipoIndicadorRequest tipoIndicadorRequest) {
        try {
            Integer result = tipoIndicadorDao.saveTipoIndicador(modelMapper.map(tipoIndicadorRequest, TipoIndicador.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteTipoIndicador(TipoIndicadorRequest tipoIndicadorRequest) {
        try {
            tipoIndicadorDao.deleteTipoIndicador(modelMapper.map(tipoIndicadorRequest, TipoIndicador.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<TipoIndicadorResponse> getByid(int id_tipo_indicador) {
        try {
            TipoIndicadorResponse tipoIndicadorResponse = modelMapper.map(tipoIndicadorDao.getByid(id_tipo_indicador), TipoIndicadorResponse.class);
            return new Response<>(true, "", tipoIndicadorResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<TipoIndicadorResponse>> listarTipoIndicadorByTipo(String buscar, SearchTipoIndicador searchTipoIndicador, int pagina, int nroPagina) {
        TipoIndicador tipoIndicador = new TipoIndicador();
        tipoIndicador.setPagina(pagina);
        tipoIndicador.setNro_pagina(nroPagina);
        tipoIndicador.setBuscar(buscar);
        try {
            List<TipoIndicadorResponse> categorias = new ArrayList<>();
            switch (searchTipoIndicador) {
                case DESCRIPCION:
                    categorias = tipoIndicadorDao.getTipoIndicadorByDescripcion(tipoIndicador).stream().map(p -> modelMapper.map(p, TipoIndicadorResponse.class)).toList();
                    break;
                case ABREVIACION:
                    categorias = tipoIndicadorDao.getTipoIndicadorByAbreviacion(tipoIndicador).stream().map(p -> modelMapper.map(p, TipoIndicadorResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", categorias);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchTipoIndicador searchTipoIndicador) {
        TipoIndicador tipoIndicador = new TipoIndicador();
        tipoIndicador.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (searchTipoIndicador) {
                case DESCRIPCION:
                    cantidad = tipoIndicadorDao.getCantidadTipoIndicadorByDescripcion(tipoIndicador);
                    break;
                case ABREVIACION:
                    cantidad = tipoIndicadorDao.getCantidadTipoIndicadorByAbreviacion(tipoIndicador);
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
    public Response<TipoIndicadorRequest> getByidTipoIndicador(int id_tipo_indicador) {
        try {
            TipoIndicadorRequest tipoIndicadorRequest = modelMapper.map(tipoIndicadorDao.getByid(id_tipo_indicador), TipoIndicadorRequest.class);
            return new Response<>(true, "", tipoIndicadorRequest);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> getTiposIndicadores() {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : tipoIndicadorDao.getAllTipoIndicador())
                listViews.add(new ListView(String.valueOf(item.getId_tipo_indicador()), String.format("%s(%s)", item.getDescripcion(), item.getAbreviacion())));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
