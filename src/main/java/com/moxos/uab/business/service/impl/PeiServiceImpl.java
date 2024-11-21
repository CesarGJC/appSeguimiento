package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IPeiService;
import com.moxos.uab.common.enums.SearchPei;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.Pei;
import com.moxos.uab.persistence.die.PeiDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeiServiceImpl implements IPeiService {
    private ModelMapper modelMapper;
    private final PeiDao peiDao;

    public PeiServiceImpl(PeiDao peiDao) {
        this.peiDao = peiDao;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Response<Integer> savePei(PeiRequest pei) {
        try {
            Integer result = peiDao.savePei(modelMapper.map(pei, Pei.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deletePei(PeiRequest pei) {
        try {
            peiDao.deletePei(modelMapper.map(pei, Pei.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<List<ListView>> listarPei(String gestion) {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : peiDao.getAllPeiByGestion(gestion))
                listViews.add(new ListView(String.valueOf(item.getId_plan_pei()), item.getDescripcion()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<PeiResponse> getByid(int id_plan_pei) {
        try {
            PeiResponse pei = modelMapper.map(peiDao.getByid(id_plan_pei), PeiResponse.class);
            return new Response<>(true, "", pei);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<PeiResponse>> listarPeiByTipo(String buscar, SearchPei searchPei, int pagina, int nroPagina) {
        Pei pei = new Pei();
        pei.setPagina(pagina);
        pei.setNro_pagina(nroPagina);
        pei.setBuscar(buscar);
        try {
            List<PeiResponse> peis = new ArrayList<>();
            switch (searchPei) {
                case GESTION:
                    peis = peiDao.getPeiByGestion(pei).stream().map(p -> modelMapper.map(p, PeiResponse.class)).toList();
                    break;
                case DESCRIPCION:
                    peis = peiDao.getPeiByPei(pei).stream().map(p -> modelMapper.map(p, PeiResponse.class)).toList();
                    break;
                default:
                    break;
            }
            return new Response<>(true, "", peis);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(String buscar, SearchPei searchPei) {
        Pei pei = new Pei();
        pei.setBuscar(buscar);
        try {
            Integer cantidad = 0;
            switch (searchPei) {
                case GESTION:
                    cantidad = peiDao.getCantidadPeiByGestion(pei);
                    break;
                case DESCRIPCION:
                    cantidad = peiDao.getCantidadPeiByPei(pei);
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
    public Response<PeiRequest> getByidPei(int id_plan_pei) {
        try {
            PeiRequest pei = modelMapper.map(peiDao.getByid(id_plan_pei), PeiRequest.class);
            return new Response<>(true, "", pei);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
