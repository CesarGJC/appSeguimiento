package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IUnidadMedidaService;
import com.moxos.uab.common.enums.SearchUnidadMedida;
import com.moxos.uab.domain.dto.request.unidadmedida.UnidadMedidaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.unidadmedida.UnidadMedidaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.UnidadMedida;
import com.moxos.uab.persistence.die.UnidadMedidaDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnidadMedidaServiceImpl implements IUnidadMedidaService {
    private ModelMapper modelMapper;
    private final UnidadMedidaDao unidadMedidaDao;


    public UnidadMedidaServiceImpl(UnidadMedidaDao unidadMedidaDao) {
        this.unidadMedidaDao = unidadMedidaDao;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Response<Integer> saveUnidadMedida(UnidadMedidaRequest unidadMedidaRequest) {
        try {
            Integer result = unidadMedidaDao.saveUnidadMedida(modelMapper.map(unidadMedidaRequest, UnidadMedida.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteUnidadMedida(UnidadMedidaRequest unidadMedidaRequest) {
        try {
            unidadMedidaDao.deleteUnidadMedida(modelMapper.map(unidadMedidaRequest, UnidadMedida.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<UnidadMedidaResponse> getByid(int id_unidad_medida) {
        try {
            UnidadMedidaResponse unidadMedidaResponse = modelMapper.map(unidadMedidaDao.getByid(id_unidad_medida), UnidadMedidaResponse.class);
            return new Response<>(true, "", unidadMedidaResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<UnidadMedidaResponse>> listarUnidadMedidaByTipo(String buscar, SearchUnidadMedida searchUnidadMedida, int pagina, int nroPagina) {
        UnidadMedida unidadMedida = new UnidadMedida();
        unidadMedida.setPagina(pagina);
        unidadMedida.setNro_pagina(nroPagina);
        unidadMedida.setBuscar(buscar);
        try {
            List<UnidadMedidaResponse> categorias = new ArrayList<>();
            switch (searchUnidadMedida) {
                case DESCRIPCION:
                    categorias = unidadMedidaDao.getUnidadMedidaByDescripcion(unidadMedida).stream().map(p -> modelMapper.map(p, UnidadMedidaResponse.class)).toList();
                    break;
                case ABREVIACION:
                    categorias = unidadMedidaDao.getUnidadMedidaByAbreviacion(unidadMedida).stream().map(p -> modelMapper.map(p, UnidadMedidaResponse.class)).toList();
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
    public Response<Integer> getCantidadByTipo(String buscar, SearchUnidadMedida searchUnidadMedida) {
        UnidadMedida unidadMedida = new UnidadMedida();
        unidadMedida.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (searchUnidadMedida) {
                case DESCRIPCION:
                    cantidad = unidadMedidaDao.getCantidadUnidadMedidaByDescripcion(unidadMedida);
                    break;
                case ABREVIACION:
                    cantidad = unidadMedidaDao.getCantidadUnidadMedidaByAbreviacion(unidadMedida);
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
    public Response<UnidadMedidaRequest> getByidUnidadMedia(int id_unidad_medida) {
        try {
            UnidadMedidaRequest unidadMedidaRequest = modelMapper.map(unidadMedidaDao.getByid(id_unidad_medida), UnidadMedidaRequest.class);
            return new Response<>(true, "", unidadMedidaRequest);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> getUnidadMedida() {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : unidadMedidaDao.getAllUnidadMedida())
                listViews.add(new ListView(String.valueOf(item.getId_unidad_medida()), String.format("%s(%s)", item.getDescripcion(), item.getAbreviacion())));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
