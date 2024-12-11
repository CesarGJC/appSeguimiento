package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IFormularioService;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.formulario.FormularioProgramacionResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.entity.die.CategoriaIndicador;
import com.moxos.uab.domain.entity.die.FormularioProgramacion;
import com.moxos.uab.persistence.die.FormularioDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormularioServiceImpl implements IFormularioService {
    private final ModelMapper modelMapper;
    private final FormularioDao formularioDao;

    public FormularioServiceImpl(ModelMapper modelMapper, FormularioDao formularioDao) {
        this.modelMapper = modelMapper;
        this.formularioDao = formularioDao;
    }

    @Override
    public Response<List<FormularioResponse>> getFormularios(FilterRequest<FormularioFilterRequest> buscar) {
        try {
            List<FormularioResponse> formularios = formularioDao.getFormularios(buscar).stream().map(p -> modelMapper.map(p, FormularioResponse.class)).toList();
            return new Response<>(true, "", formularios);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(FilterRequest<FormularioFilterRequest> buscar) {
        try {
            Integer cantidad = formularioDao.getCantidadFormularios(buscar);
            return new Response<>(true, "", cantidad);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> saveFormularioProgramacion(FormularioRequest model) {
        try {
            Integer result = formularioDao.saveCategoriaIndicador(modelMapper.map(model, FormularioProgramacion.class));
            return new Response<Integer>(true, "", result);
        } catch (Exception e) {
            return new Response<Integer>(false, e.getMessage(), -1);
        }
    }

    @Override
    public Response<FormularioRequest> getByid(int id) {
        try {
            FormularioRequest result = modelMapper.map(formularioDao.getByid(id), FormularioRequest.class);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<FormularioProgramacionResponse> getFormularioProgramacionDetalle(int id) {
        try {
            FormularioProgramacionResponse result = modelMapper.map(formularioDao.getFormularioDetalle(id), FormularioProgramacionResponse.class);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse deleteFormulario(Integer id) {
        try {
            formularioDao.deleteFormulario(id);
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }
}
