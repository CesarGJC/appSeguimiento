package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IPoliticasDesarrolloService;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.PoliticasDesarrollo;
import com.moxos.uab.persistence.die.PoliticasDesarrolloDao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PoliticasDesarrolloServiceImpl implements IPoliticasDesarrolloService {
    private ModelMapper modelMapper;
    private final PoliticasDesarrolloDao politicasDesarrolloDao;

    public PoliticasDesarrolloServiceImpl(PoliticasDesarrolloDao politicasDesarrolloDao,ModelMapper moodelMapper ) {
        this.politicasDesarrolloDao = politicasDesarrolloDao;
        this.modelMapper = moodelMapper;
    }
    @Override
    public GeneralResponse savePoliticasDesarrollo(PoliticasDesarrolloRequest pd) {
        try{
            politicasDesarrolloDao.savePoliticasDesarrollo(modelMapper.map(pd, PoliticasDesarrollo.class));
            return new GeneralResponse(true, "");
        }catch(Exception e){
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public GeneralResponse updatePoliticasDesarrollo(PoliticasDesarrolloRequest pd) {
        try{
            politicasDesarrolloDao.updatePoliticasDesarrollo(modelMapper.map(pd,PoliticasDesarrollo.class));
            return new GeneralResponse(true, "");
        }catch (Exception e){
            return new GeneralResponse(false, e.getMessage());
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
    public Response<PoliticasDesarrolloRequest> getByid(int id_politica_desarrollo) {
        try{
            PoliticasDesarrolloRequest politicasDesarrollo=modelMapper.map(politicasDesarrolloDao.getByid(id_politica_desarrollo),PoliticasDesarrolloRequest.class);
            return new Response<>(true,"",politicasDesarrollo);
        }catch (Exception e){
            return new Response<>(false,e.getMessage(),null);
        }
    }
}
