package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.adjuntos.AdjuntosRequest;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.adjuntos.AdjuntosResponse;
import com.moxos.uab.domain.dto.response.adjuntos.DocumentosTiposAdjuntosResponse;
import jakarta.servlet.http.HttpServletResponse;


public interface IAdjuntarDocumentosFacade {
    DocumentosTiposAdjuntosResponse getDocumentosPorActividad(Integer id);

    AdjuntosRequest getDocumentoRequest(Integer id);

    Response<AdjuntosResponse> saveDocumentoAdjunto(AdjuntosRequest model);

    void downloadDocumentoAdjunto(Integer id, HttpServletResponse response);

    void downloadDocumentoAdjuntoDocumento(Integer id, HttpServletResponse response);
}
