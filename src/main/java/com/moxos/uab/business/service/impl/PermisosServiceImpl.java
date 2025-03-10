package com.moxos.uab.business.service.impl;


import com.moxos.uab.business.service.IPermisosService;
import com.moxos.uab.domain.dto.request.permisos.AsignarPermisosRequest;
import com.moxos.uab.domain.dto.request.permisos.PermisosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.permisos.PermisoResponse;
import com.moxos.uab.domain.dto.response.permisos.PermisosUnidadResponse;
import com.moxos.uab.domain.entity.die.Permisos;
import com.moxos.uab.persistence.die.PermisosDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermisosServiceImpl implements IPermisosService {
    private final PermisosDao permisosDao;
    private final ModelMapper modelMapper;

    public PermisosServiceImpl(PermisosDao permisosDao, ModelMapper modelMapper) {
        this.permisosDao = permisosDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public GeneralResponse savePermisos(AsignarPermisosRequest permisosRequest) {
        try {
            List<Integer> errors = new ArrayList<>();
            for (Integer ids : permisosRequest.getId_unidad()) {
                Permisos permisos = new Permisos();
                permisos.setId_formulario(permisosRequest.getId_formulario());
                permisos.setId_tipo_unidad(permisosRequest.getId_tipo_unidad());
                permisos.setId_unidad(ids);
                permisos.setUlt_usuario(permisosRequest.getUlt_usuario());
                errors.add(permisosDao.savePermisos(permisos));
            }
            return new GeneralResponse(errors.isEmpty(), errors.isEmpty() ? "" : String.format("No se agrego %s correctamente algunos elementos {%s}", errors.size(), errors.toString()));
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public GeneralResponse habilitarPermisos(Integer idPermisos, boolean habilitar) {
        try {
            Permisos permisos = new Permisos();
            permisos.setId_permisos(idPermisos);
            permisos.setHabilitado(habilitar);
            permisosDao.habilitarPermisos(permisos);
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
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
    public Response<PermisoResponse> getByid(int id_permisos) {
        try {
            PermisoResponse permisoResponse = modelMapper.map(permisosDao.getByid(id_permisos), PermisoResponse.class);
            return new Response<>(true, "", permisoResponse);
        } catch (Exception e) {
            return new Response<>(false, "", null);
        }
    }

    @Override
    public Response<List<PermisosUnidadResponse>> getListaUnidadesAdministrativas(Integer idTipoUnidad, Integer idFormulario) {
        try {
            var permisosResponse = permisosDao.getListaUnidadesAdministrativas(idTipoUnidad, idFormulario)
                    .stream().map(p -> modelMapper.map(p, PermisosUnidadResponse.class)).collect(Collectors.toList());
            return new Response<>(!permisosResponse.isEmpty(), permisosResponse.isEmpty() ? "la lista de permisos no contiene elementos" : "", permisosResponse);
        } catch (Exception e) {
            return new Response<>(false, "", null);
        }
    }
}
