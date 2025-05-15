package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.descripcionoperacionespoa.DescripcionOperacionesPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.descripcionoperacionespoa.DescripcionOperacionesPoaResponse;
import com.moxos.uab.domain.entity.die.DescripcionOperacionesPoa;

import java.util.List;

public interface IDescripcionOperacionesPoaService {
    Response<Integer> saveDescripcionOperacionesPoa(DescripcionOperacionesPoaRequest descripcionOperacionesPoaRequest);

    GeneralResponse deleteDescripcionOperacionesPoa(DescripcionOperacionesPoaRequest descripcionOperacionesPoaRequest);

    Response<DescripcionOperacionesPoaResponse> getByid(int id_descripcion_operaciones_poa);

    GeneralResponse esPorcentaje(int id_descripcion_operaciones_poa);
}
