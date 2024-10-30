package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.ICatalogoIndicadoresService;
import com.moxos.uab.common.enums.SearchCatalogo;
import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.CatalogoIndicadores;
import com.moxos.uab.persistence.die.CatalogoIndicadoresDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogoIndicadoresServiceImpl implements ICatalogoIndicadoresService {
    private ModelMapper modelMapper;
    private final CatalogoIndicadoresDao catalogoIndicadoresDao;

    public CatalogoIndicadoresServiceImpl(CatalogoIndicadoresDao catalogoIndicadoresDao, ModelMapper modelMapper) {
        this.catalogoIndicadoresDao = catalogoIndicadoresDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<CatalogoIndicadoresResponse>saveCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores) {
        try {
            Integer id= catalogoIndicadoresDao.saveCatalogoIndicadores(modelMapper.map(catalogoIndicadores,CatalogoIndicadores.class));
            CatalogoIndicadoresResponse catalogoIndicadoresResponse = modelMapper.map(catalogoIndicadoresDao.getByid(id),CatalogoIndicadoresResponse.class);
            return new Response<>(true,"",catalogoIndicadoresResponse);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public GeneralResponse deleteCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores) {
        try {
            catalogoIndicadoresDao.deleteCatalogoIndicadores(modelMapper.map(catalogoIndicadores, CatalogoIndicadores.class));
            return new GeneralResponse(true,"");
        }catch (Exception e){
            return new GeneralResponse(false,e.getMessage());
        }
    }

    @Override
    public Response<List<CatalogoIndicadoresResponse>> selectCatalogoIndicadores() {
        try {
            List<CatalogoIndicadoresResponse> catalogoIndicadores = modelMapper.map(catalogoIndicadoresDao.getAllCatalogoIndicadores(),List.class);
            return  new Response<>(true,"",catalogoIndicadores);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<List<ListView>> listCatalogoIndicadores() {
        try{
            List<ListView> listviews =new ArrayList<>();
            for(var item : catalogoIndicadoresDao.getAllCatalogoIndicadores())
                listviews.add(new ListView(String.valueOf(item.getCatalogo_indicador()),item.getCatalogo_indicador()));
            return new Response<>(true,"",listviews);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<CatalogoIndicadoresRequest> getByid(int id_catalogo_indicador) {
        try {
            CatalogoIndicadoresRequest catalogoIndicadores= modelMapper.map(catalogoIndicadoresDao.getByid(id_catalogo_indicador),CatalogoIndicadoresRequest.class);
            return new Response<>(true,"",catalogoIndicadores);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }

    @Override
    public Response<List<CatalogoIndicadoresResponse>> listarCatalogoIndicadoresByTipo(String buscar, SearchCatalogo SearchIndicadores, int pagina, int nroPagina) {
        CatalogoIndicadores catalogoIndicadores = new CatalogoIndicadores();
        catalogoIndicadores.setPagina(pagina);
        catalogoIndicadores.setNro_pagina(nroPagina);
        catalogoIndicadores.setBuscar(buscar);
        try {
            List<CatalogoIndicadoresResponse> indicadoresEstrategicos=new ArrayList<>();
            switch (SearchIndicadores){
                case META:
                    indicadoresEstrategicos=catalogoIndicadoresDao.getCatalogoIndicadoresByMeta(catalogoIndicadores).stream().map(p->modelMapper.map(p,CatalogoIndicadoresResponse.class)).toList();
                    break;
                case INDICADORES_ESTRATEGICOS:
                    indicadoresEstrategicos=catalogoIndicadoresDao.getCatalogoIndicadoresByIndicadores(catalogoIndicadores).stream().map(p->modelMapper.map(p,CatalogoIndicadoresResponse.class)).toList();
                    break;
                case DESCRIPCION:
                    indicadoresEstrategicos=catalogoIndicadoresDao.getCatalogoIndicadoresByCatalogo(catalogoIndicadores).stream().map(p->modelMapper.map(p,CatalogoIndicadoresResponse.class)).toList();
                    break;
                case LINEA_BASE:
                    indicadoresEstrategicos=catalogoIndicadoresDao.getCatalogoIndicadoresByLineaBase(catalogoIndicadores).stream().map(p->modelMapper.map(p,CatalogoIndicadoresResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true,"",indicadoresEstrategicos);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }


    }


    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchCatalogo value) {
        CatalogoIndicadores catalogoIndicadores = new CatalogoIndicadores();
        catalogoIndicadores.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (value){
                case META:
                    cantidad=catalogoIndicadoresDao.getCantidadCatalogoIndicadoresByMeta(catalogoIndicadores);
                    break;
                case INDICADORES_ESTRATEGICOS:
                    cantidad=catalogoIndicadoresDao.getCantidadCatalogoIndicadoresByIndicadores(catalogoIndicadores);;
                    break;
                case DESCRIPCION:
                    cantidad=catalogoIndicadoresDao.getCantidadCatalogoIndicadoresByCatalogo(catalogoIndicadores);;
                    break;
                case LINEA_BASE:
                    cantidad=catalogoIndicadoresDao.getCantidadCatalogoIndicadoresByLineaBase(catalogoIndicadores);;
                    break;
                default:
                    break;
            }
            return new Response<>(true,"",cantidad);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }
}
