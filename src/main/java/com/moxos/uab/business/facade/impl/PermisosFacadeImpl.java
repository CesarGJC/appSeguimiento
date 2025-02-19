package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IEncargadosUnidadFacade;
import com.moxos.uab.business.service.IPermisosService;
import com.moxos.uab.domain.dto.request.permisos.PermisosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.permisos.PermisosResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EncargadoUnidadFacadeImpl implements IEncargadosUnidadFacade {
    private final ModelMapper modelMapper;
    private final IPermisosService permisosService;

    public EncargadoUnidadFacadeImpl(ModelMapper modelMapper, IPermisosService permisosService) {
        this.modelMapper = modelMapper;
        this.permisosService = permisosService;
    }

    @Override
    public Response<PermisosResponse> savePermisos(PermisosRequest permisosRequest) {
        Response<Integer> result = permisosService.savePermisos(permisosRequest);
        return permisosService.getByid(result.getResult());
    }

    @Override
    public GeneralResponse deletePermisos(PermisosRequest permisos) {
        return permisosService.deletePermisos(permisos);
    }

    @Override
    public PermisosRequest getPermisosModel(int idPermisos) {
        var result = permisosService.getByid(idPermisos);
        return modelMapper.map(result, PermisosRequest.class);
    }
}
