package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IIndicadoresEstrategicosService;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.IndicadoresEstrategicos;
import com.moxos.uab.persistence.die.IndicadoresEstrategicosDao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class IndicadoresEstrategicosServiceImpl implements IIndicadoresEstrategicosService {
    private ModelMapper modelMapper;
    private final IndicadoresEstrategicosDao indicadoresEstrategicosDao;

    public IndicadoresEstrategicosServiceImpl(IndicadoresEstrategicosDao indicadoresEstrategicosDao, ModelMapper modelMapper) {
        this.indicadoresEstrategicosDao = indicadoresEstrategicosDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public GeneralResponse saveIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos) {
        try {
            indicadoresEstrategicosDao.saveIndicadoresEstrategicos(modelMapper.map(indicadoresEstrategicos,IndicadoresEstrategicos.class));
            return new GeneralResponse(true,"");
        }catch (Exception e){
            return new GeneralResponse(false,e.getMessage());
        }
    }

    @Override
    public GeneralResponse updateIndicadoresEstrategicos(IndicadoresEstrategicosRequest indicadoresEstrategicos) {
        try {
            indicadoresEstrategicosDao.updateIndicadoresEstrategicos(modelMapper.map(indicadoresEstrategicos,IndicadoresEstrategicos.class));
            return new GeneralResponse(true,"");
        }catch (Exception e){
            return new GeneralResponse(false,e.getMessage());
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
}
