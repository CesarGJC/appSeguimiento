package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.adjuntos.AdjuntosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.adjuntos.AdjuntosResponse;

import java.util.List;

public interface IAdjuntarService {
    Response<AdjuntosResponse> getAdjuntoDetallePorId(Integer id);

    Response<AdjuntosRequest> getAdjuntoPorId(Integer id);

    Response<List<AdjuntosResponse>> getListaAdjuntoPorActvidades(Integer idOpreciones, String tipoDocumento);

    GeneralResponse addAdjunto(AdjuntosRequest adjunto);

    Response<AdjuntosResponse> getAdjuntoDetalleDocumentoPorIdActividad(Integer id);
}
