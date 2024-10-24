package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IIndicadoresEstrategicosService;
import com.moxos.uab.common.enums.SearchIndicadores;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.IndicadoresEstrategicos;
import com.moxos.uab.persistence.die.IndicadoresEstrategicosDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class IndicadoresEstrategicosServiceImpl implements IIndicadoresEstrategicosService {
    private ModelMapper modelMapper;
    private final IndicadoresEstrategicosDao indicadoresEstrategicosDao;

    public IndicadoresEstrategicosServiceImpl(IndicadoresEstrategicosDao indicadoresEstrategicosDao, ModelMapper modelMapper) {
        this.indicadoresEstrategicosDao = indicadoresEstrategicosDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public Response<IndicadoresEstrategicosResponse> saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest pd) {
        try {
            Integer id= indicadoresEstrategicosDao.saveIndicadoresEstrategicos(modelMapper.map(pd,IndicadoresEstrategicos.class));
            IndicadoresEstrategicosResponse indicadoresEstrategicosResponse=modelMapper.map(indicadoresEstrategicosDao.getById(id),IndicadoresEstrategicosResponse.class);
            return new Response<>(true,"",indicadoresEstrategicosResponse);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public GeneralResponse deleteIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos) {
        try {
            indicadoresEstrategicosDao.deleteIndicadoresEstrategicos(modelMapper.map(indicadoresEstrategicos,IndicadoresEstrategicos.class));
            return new GeneralResponse(true,"");
        }catch (Exception e){
            return new GeneralResponse(false,e.getMessage());
        }
    }

    @Override
    public Response<List<IndicadoresEstrategicosResponse>> selectIndicadoresEstrategicos() {
        try{
            List<IndicadoresEstrategicosResponse> indicadoresEstrategicos = modelMapper.map(indicadoresEstrategicosDao.getAllIndicadoresEstrategicos(),List.class);
            return new Response<>(true,"",indicadoresEstrategicos);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<List<ListView>> listIndicadoresEstrategicos() {
        try{
            List<ListView> listViews=new ArrayList<>();
            for (var item : indicadoresEstrategicosDao.getAllIndicadoresEstrategicos())
                listViews.add(new ListView(String.valueOf(item.getId_indicador_estrategico()),item.getIndicador_estrategico()));

            return new Response<>(true,"",listViews);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<IndicadoresEstrategicosRequest> getById(int id_indicador_estrategico) {
        try{
            IndicadoresEstrategicosRequest indicadoresEstrategicos = modelMapper.map(indicadoresEstrategicosDao.getById(id_indicador_estrategico),IndicadoresEstrategicosRequest.class);
            return new Response<>(true,"",indicadoresEstrategicos);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<List<IndicadoresEstrategicosResponse>> listarIndicadoresEstrategicosByTipo(String buscar, SearchIndicadores SearchPoliticas, int pagina, int nroPagina) {
        IndicadoresEstrategicos indicadoresEstrategicos = new IndicadoresEstrategicos();
        indicadoresEstrategicos.setPagina(pagina);
        indicadoresEstrategicos.setNro_pagina(nroPagina);
        indicadoresEstrategicos.setBuscar(buscar);
        try {
            List<IndicadoresEstrategicosResponse> politicasDesarrollo = new ArrayList<>();
            switch (SearchPoliticas) {
                case DESCRIPCION:
                    politicasDesarrollo = indicadoresEstrategicosDao.getIndicadoresEstrategicosByIndicadores(indicadoresEstrategicos).stream().map(p -> modelMapper.map(p, IndicadoresEstrategicosResponse.class)).toList();
                    break;
                case POLITICAS_DESARROLO:
                    politicasDesarrollo = indicadoresEstrategicosDao.getIndicadoresEstrategicosByPolitica(indicadoresEstrategicos).stream().map(p -> modelMapper.map(p, IndicadoresEstrategicosResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", politicasDesarrollo);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchIndicadores value) {
        IndicadoresEstrategicos indicadoresEstrategicos = new IndicadoresEstrategicos();
        indicadoresEstrategicos.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (value) {
                case DESCRIPCION:
                    cantidad = indicadoresEstrategicosDao.getCantidadIndicadoresEstrategicosByIndicadores(indicadoresEstrategicos);
                    break;
                case POLITICAS_DESARROLO:
                    cantidad = indicadoresEstrategicosDao.getCantidadIndicadoresEstrategicosByPolitica(indicadoresEstrategicos);
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", cantidad);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
