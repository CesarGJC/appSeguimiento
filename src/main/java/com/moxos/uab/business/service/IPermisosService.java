package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.permisos.AsignarPermisosRequest;
import com.moxos.uab.domain.dto.request.permisos.PermisosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.permisos.PermisoResponse;
import com.moxos.uab.domain.dto.response.permisos.PermisosUnidadResponse;

import java.util.List;

public interface IPermisosService {

    GeneralResponse savePermisos(AsignarPermisosRequest permisosRequest);

    GeneralResponse habilitarPermisos(Integer idPermisos, boolean habilitar);

    GeneralResponse deletePermisos(PermisosRequest permisosRequest);

    Response<PermisoResponse> getByid(int id_permisos);

    Response<List<PermisosUnidadResponse>> getListaUnidadesAdministrativas(Integer idTipoUnidad, Integer idFormulario);
}
