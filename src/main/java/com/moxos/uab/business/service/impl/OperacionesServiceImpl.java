package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IOperacionesService;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesFilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.request.operaciones.ProgramasActividadesUnidadRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.FormularioActividadesResponse;
import com.moxos.uab.domain.entity.die.OperacionesActividades;
import com.moxos.uab.persistence.die.OperacionesDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OperacionesServiceImpl implements IOperacionesService {
    private final ModelMapper modelMapper;
    private final OperacionesDao operacionesDao;

    public OperacionesServiceImpl(ModelMapper modelMapper, OperacionesDao operacionesDao) {
        this.modelMapper = modelMapper;
        this.operacionesDao = operacionesDao;
    }

    @Override
    public Response<Integer> saveOperaciones(OperacionesRequest operacionesRequest) {
        try {
            UUID uuid = UUID.randomUUID();
            OperacionesActividades operacionesActividades = modelMapper.map(operacionesRequest, OperacionesActividades.class);
            operacionesActividades.setDocumento(uuid.toString());
            Integer result = operacionesDao.saveOperaciones(operacionesActividades);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteOperaciones(OperacionesRequest operacionesRequest) {
        try {
            operacionesDao.deleteOperaciones(modelMapper.map(operacionesRequest, OperacionesActividades.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<OperacionesRequest> getByid(int idOperaciones) {
        try {
            OperacionesRequest operacionesRequest = modelMapper.map(operacionesDao.getByid(idOperaciones), OperacionesRequest.class);
            return new Response<>(true, "", operacionesRequest);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<FormularioActividadesResponse>> getFormulariosActividades(FilterRequest<OperacionesFilterRequest> buscar, ProgramasActividadesUnidadRequest model) {
        try {
            List<FormularioActividadesResponse> formularios = operacionesDao.getFormulariosActividades(buscar, model.getId_descripcion_operaciones_poa()).stream().map(p -> modelMapper.map(p, FormularioActividadesResponse.class)).toList();
            return new Response<>(true, "", formularios);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(FilterRequest<OperacionesFilterRequest> buscar, ProgramasActividadesUnidadRequest model) {
        try {
            Integer cantidad = operacionesDao.getCantidadByTipo(buscar, model.getId_descripcion_operaciones_poa());
            return new Response<>(true, "", cantidad);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }


}
