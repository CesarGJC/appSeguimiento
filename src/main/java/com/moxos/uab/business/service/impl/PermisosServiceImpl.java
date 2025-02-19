package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IPermisosService;
import com.moxos.uab.domain.dto.request.permisos.PermisosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.permisos.PermisosResponse;
import com.moxos.uab.domain.entity.die.Permisos;
import com.moxos.uab.persistence.die.PermisosDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PermisosServiceImpl implements IPermisosService {
    private final PermisosDao permisosDao;
    private final ModelMapper modelMapper;

    public PermisosServiceImpl(PermisosDao permisosDao, ModelMapper modelMapper) {
        this.permisosDao = permisosDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<Integer> savePermisos(PermisosRequest permisosRequest) {
        try {
            var value = modelMapper.map(permisosRequest, Permisos.class);
            Integer result = permisosDao.savePermisos(value);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deletePermisos(PermisosRequest permisosRequest) {
        try {
            permisosDao.deletePermisos(modelMapper.map(permisosRequest, Permisos.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<PermisosResponse> getByid(int id_permisos) {
        try{
            PermisosResponse permisosResponse = modelMapper.map(permisosDao.getByid(id_permisos), PermisosResponse.class);
            return new Response<>(true, "", permisosResponse);
        }catch(Exception e){
            return new Response<>(false,"",null);
        }
    }
}
