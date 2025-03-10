package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IDepartamentoService;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.ProgramaResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.persistence.siiga.DepartamentosDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {
    private final DepartamentosDao departamentosDao;
    private final ModelMapper modelMapper;

    public DepartamentoServiceImpl(DepartamentosDao departamentosDao, ModelMapper modelMapper) {
        this.departamentosDao = departamentosDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<ProgramaResponse> getDepartamento(int idDepartamento) {
        try {
            var response = departamentosDao.getDepartamentoPorId(idDepartamento);
            ProgramaResponse programaResponse = new ProgramaResponse();
            programaResponse.setId_programa(response.getId_departamento());
            programaResponse.setPrograma(response.getDepartamento());
            return new Response<>(true, "", programaResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<ListView>> getListaDepartamentos() {
        try {
            List<ListView> programas = new ArrayList<>();
            var response = departamentosDao.getListarUnidadesDepartamentos()
                    .stream().map(p -> new ListView(String.valueOf(p.getId_departamento()), p.getDepartamento()))
                    .collect(Collectors.toList());
            return new Response<>(!response.isEmpty(), response.isEmpty() ? "La lista de departamentos se encuentra vaciá" : "", response);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
