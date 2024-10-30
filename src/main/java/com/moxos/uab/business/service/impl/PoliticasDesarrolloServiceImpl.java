package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IPoliticasDesarrolloService;
import com.moxos.uab.common.enums.SearchPoliticas;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.PoliticasDesarrollo;
import com.moxos.uab.persistence.die.PoliticasDesarrolloDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PoliticasDesarrolloServiceImpl implements IPoliticasDesarrolloService {
    private ModelMapper modelMapper;
    private final PoliticasDesarrolloDao politicasDesarrolloDao;

    public PoliticasDesarrolloServiceImpl(PoliticasDesarrolloDao politicasDesarrolloDao,ModelMapper moodelMapper ) {
        this.politicasDesarrolloDao = politicasDesarrolloDao;
        this.modelMapper = moodelMapper;
    }
    @Override
    public Response<PoliticasDesarrolloResponse> savePoliticasDesarrollo(PoliticasDesarrolloRequest pd) {
        try{
           Integer id= politicasDesarrolloDao.savePoliticasDesarrollo(modelMapper.map(pd, PoliticasDesarrollo.class));
            PoliticasDesarrolloResponse politicasDesarrolloResponse=modelMapper.map(politicasDesarrolloDao.getByid(id),PoliticasDesarrolloResponse.class);
            return new Response<>(true, "",politicasDesarrolloResponse);
        }catch(Exception e){
            return new Response<>(false, e.getMessage(),null);
        }
    }


    @Override
    public GeneralResponse deletePoliticasDesarrollo(PoliticasDesarrolloRequest pd) {
        try{
            politicasDesarrolloDao.deletePoliticasDesarrollo(modelMapper.map(pd,PoliticasDesarrollo.class));
            return new GeneralResponse(true, "");
        }catch (Exception e){
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<List<PoliticasDesarrolloResponse>> selectPoliticasDesarrollo() {
        try{
            List<PoliticasDesarrolloResponse> politicasDesarrollo=modelMapper.map(politicasDesarrolloDao.getAllPoliticasDesarrollo(),List.class);
            return new Response<>(true,"",politicasDesarrollo);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<List<ListView>> listPoliticasDesarrollo() {
         try{
             List<ListView> listViews=new ArrayList<>();
             for (var item:politicasDesarrolloDao.getAllPoliticasDesarrollo())
                 listViews.add(new ListView(String.valueOf(item.getId_politica_desarrollo()), item.getPolitica_desarrollo()));
             return new Response<>(true,"",listViews);
         }catch (Exception e){
             return new Response<>(false,e.getMessage(),null);
         }
    }
    @Override
    public Response<List<ListView>> listPoliticasDesarrolloPorArea(Integer idAreaEstrategica) {
        try{
            List<ListView> listViews=new ArrayList<>();
            for (var item:politicasDesarrolloDao.getAllPoliticasDesarrolloPorArea(idAreaEstrategica))
                listViews.add(new ListView(String.valueOf(item.getId_politica_desarrollo()), item.getPolitica_desarrollo()));
            return new Response<>(true,"",listViews);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }
    @Override
    public Response<PoliticasDesarrolloRequest> getByid(int id_politica_desarrollo) {
        try{
            PoliticasDesarrolloRequest politicasDesarrollo=modelMapper.map(politicasDesarrolloDao.getByid(id_politica_desarrollo),PoliticasDesarrolloRequest.class);
            return new Response<>(true,"",politicasDesarrollo);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<List<PoliticasDesarrolloResponse>> listarPoliticasDesarrolloByTipo(String buscar, SearchPoliticas searchAreas, int pagina, int nroPagina) {
        PoliticasDesarrollo politicasDesarrollo = new PoliticasDesarrollo();
        politicasDesarrollo.setPagina(pagina);
        politicasDesarrollo.setNro_pagina(nroPagina);
        politicasDesarrollo.setBuscar(buscar);
        try {
            List<PoliticasDesarrolloResponse> areaEstrategicas = new ArrayList<>();
            switch (searchAreas) {
                case DESCRIPCION:
                    areaEstrategicas = politicasDesarrolloDao.getPoliticasDesarrolloByPolitica(politicasDesarrollo).stream().map(p -> modelMapper.map(p, PoliticasDesarrolloResponse.class)).toList();
                    break;
                case AREAS_ESTRATEGICAS:
                    areaEstrategicas = politicasDesarrolloDao.getPoliticasDesarrolloByArea(politicasDesarrollo).stream().map(p -> modelMapper.map(p, PoliticasDesarrolloResponse.class)).toList();
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
    public Response<Integer> getCantidadByTipo(String buscar, SearchPoliticas value) {
        PoliticasDesarrollo politicasDesarrollo = new PoliticasDesarrollo();
        politicasDesarrollo.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (value) {
                case DESCRIPCION:
                    cantidad = politicasDesarrolloDao.getCantidadPoliticasDesarrolloByPolitica(politicasDesarrollo);
                    break;
                case AREAS_ESTRATEGICAS:
                    cantidad = politicasDesarrolloDao.getCantidadPoliticasDesarrolloByArea(politicasDesarrollo);
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
    public Response<List<ListView>> getAllPoliticasDesarrolloA() {
        try{
            List<ListView> listViews=new ArrayList<>();
            for (var item:politicasDesarrolloDao.getAllPoliticasDesarrolloA())
                listViews.add(new ListView(String.valueOf(item.getId_politica_desarrollo()), item.getPolitica_desarrollo()));
            return new Response<>(true,"",listViews);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }
}
