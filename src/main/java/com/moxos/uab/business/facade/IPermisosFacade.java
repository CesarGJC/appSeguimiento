package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.permisos.PermisosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.permisos.PermisosResponse;

public interface IEncargadosUnidadFacade {

    Response<PermisosResponse> savePermisos(PermisosRequest permisosRequest);

    GeneralResponse deletePermisos(PermisosRequest permisos);

    PermisosRequest getPermisosModel(int idPermisos);

}
