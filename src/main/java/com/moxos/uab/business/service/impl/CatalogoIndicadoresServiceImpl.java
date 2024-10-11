package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.ICatalogoIndicadoresService;
import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.CatalogoIndicadores;
import com.moxos.uab.persistence.die.CatalogoIndicadoresDao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CatalogoIndicadoresServiceImpl implements ICatalogoIndicadoresService {
    private ModelMapper modelMapper;
    private final CatalogoIndicadoresDao catalogoIndicadoresDao;

    public CatalogoIndicadoresServiceImpl(CatalogoIndicadoresDao catalogoIndicadoresDao, ModelMapper modelMapper) {
        this.catalogoIndicadoresDao = catalogoIndicadoresDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public GeneralResponse saveCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores) {
        try {
            catalogoIndicadoresDao.saveCatalogoIndicadores(modelMapper.map(catalogoIndicadores, CatalogoIndicadores.class));
            return new GeneralResponse(true,"");
        }catch (Exception e){
            return new GeneralResponse(false,e.getMessage());
        }
    }

    @Override
    public GeneralResponse updateCatalogoIndicadores(CatalogoIndicadoresRequest catalogoIndicadores) {
        try {
            catalogoIndicadoresDao.updateCatalogoIndicadores(modelMapper.map(catalogoIndicadores, CatalogoIndicadores.class));
            return new GeneralResponse(true,"");
        }catch (Exception e){
            return new GeneralResponse(false,e.getMessage());
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
}
