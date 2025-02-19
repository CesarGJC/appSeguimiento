package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IProgramaSevice;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.persistence.siiga.ProgramasDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramaSeviceImpl implements IProgramaSevice {
    private final ProgramasDao programasDao;
    private final ModelMapper modelMapper;

    public ProgramaSeviceImpl(ProgramasDao programasDao, ModelMapper modelMapper) {
        this.programasDao = programasDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<ProgramaResponse> getPrograma(int idPrograma) {
        try {
            var response = modelMapper.map(programasDao.getProgramaFacultad(idPrograma), ProgramaResponse.class);
            return new Response<>(true, "", response);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> getListaProgramas(int idFacultad) {
        try {
            List<ListView> programas = new ArrayList<>();
            var response = programasDao.getProgramasPorFacultad(idFacultad);
            for (var item : response) {
                programas.add(new ListView(String.valueOf(item.getId_programa()), item.getPrograma()));
            }
            return new Response<>(true, "", programas);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
