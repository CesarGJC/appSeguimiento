package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.permisos.PermisosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.permisos.PermisosResponse;

public interface IPermisosService {

    Response<Integer> savePermisos (PermisosRequest permisosRequest);

    GeneralResponse deletePermisos (PermisosRequest permisosRequest);

    Response<PermisosResponse> getByid (int id_permisos);
}
