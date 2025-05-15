package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.accioncortoplazopoa.AccionCortoPlazoPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import com.moxos.uab.domain.entity.die.AccionCortoPlazoPoa;

import java.util.List;

public interface IAccionCortoPlazoPoaService {
    Response<Integer> saveAccionCortoPlazoPoa(AccionCortoPlazoPoaRequest accionCortoPlazoPoaRequest);

    GeneralResponse deleteAccionCortoPlazoPoa(AccionCortoPlazoPoaRequest accionCortoPlazoPoaRequest);

    Response<AccionCortoPlazoPoaResponse> getByid(int id_accion_corto_plazo_poa);

    Response<List<AccionCortoPlazoPoaResponse>> getDetalleByid(int id_accion_corto_plazo_poa);

    Response<List<AccionCortoPlazoPoaResponse>> getAccionCortoPlazo(int id_accion_corto_plazo_poa);
}
