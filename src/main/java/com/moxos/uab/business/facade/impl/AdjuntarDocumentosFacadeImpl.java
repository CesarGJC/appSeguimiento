package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IAdjuntarDocumentosFacade;
import com.moxos.uab.business.service.IAdjuntarService;
import com.moxos.uab.business.service.impl.ArchivoService;
import com.moxos.uab.business.service.impl.DownloadService;
import com.moxos.uab.common.enums.TipoDcumentosEnums;
import com.moxos.uab.domain.dto.request.adjuntos.AdjuntosRequest;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.adjuntos.AdjuntosResponse;
import com.moxos.uab.domain.dto.response.adjuntos.DocumentosTiposAdjuntosResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;

@Service
@Transactional
public class AdjuntarDocumentosFacadeImpl implements IAdjuntarDocumentosFacade {
    private final IAdjuntarService adjuntarService;
    private final ArchivoService archivoService;
    private final DownloadService downloadService;
    private static final Logger logger = LogManager.getLogger(AdjuntarDocumentosFacadeImpl.class);

    public AdjuntarDocumentosFacadeImpl(IAdjuntarService adjuntarService, ArchivoService archivoService, DownloadService downloadService) {
        this.adjuntarService = adjuntarService;
        this.archivoService = archivoService;
        this.downloadService = downloadService;
    }

    public AdjuntosRequest getDocumentoRequest(Integer id) {
        var request = adjuntarService.getAdjuntoPorId(id);
        if (!request.isSuccess()) {
            logger.error(String.format("No existe el documento con el id %s", id));
            return null;
        }
        return request.getResult();
    }

    @Override
    public Response<AdjuntosResponse> saveDocumentoAdjunto(AdjuntosRequest model) {
        try {
            var responseFile = archivoService.guardarArchivo(model.getFile(), String.format("%s-%s", model.getId_adjunto(), model.getDocumento()), model.getDocumento(), model.getTipo_documento());
            if (responseFile.isSuccess()) {
                model.setNombre_documento(model.getFile().getOriginalFilename());
                model.setRuta(responseFile.getResult());
                var responseData = adjuntarService.addAdjunto(model);
                if (responseData.isSuccess()) {
                    return new Response<>(true, "", adjuntarService.getAdjuntoDetallePorId(model.getId_adjunto()).getResult());
                }
                return new Response<>(false, responseData.getMessage(), null);
            } else {
                return new Response<>(false, responseFile.getMessage(), null);
            }
        } catch (IOException e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public void downloadDocumentoAdjunto(Integer id, HttpServletResponse response) {
        var request = adjuntarService.getAdjuntoDetallePorId(id).getResult();
        try {
            downloadService.downloadResource(request.getRuta(), request.getNombre_documento(), request.getDocumento(), response);
        } catch (IOException e) {
            logger.error("Error al leer el documento con el id " + id);
        }
    }

    @Override
    public void downloadDocumentoAdjuntoDocumento(Integer id, HttpServletResponse response) {
        var request = adjuntarService.getAdjuntoDetalleDocumentoPorIdActividad(id).getResult();
        try {
            downloadService.downloadResource(request.getRuta(), request.getNombre_documento(), request.getDocumento(), response);
        } catch (IOException e) {
            logger.error("Error al leer el documento con el id " + id);
        }
    }

    @Override
    public DocumentosTiposAdjuntosResponse getDocumentosPorActividad(Integer id) {
        DocumentosTiposAdjuntosResponse documentosTiposAdjuntosResponse = new DocumentosTiposAdjuntosResponse();
        var responseImagenes = adjuntarService.getListaAdjuntoPorActvidades(id, TipoDcumentosEnums.IMAGEN);
        var responseDocumentos = adjuntarService.getListaAdjuntoPorActvidades(id, TipoDcumentosEnums.DOCUMENTO);
        if (!responseDocumentos.isSuccess()) {
            logger.info("la lista de documentos se encuentra vacia");
            documentosTiposAdjuntosResponse.setDocumentos(new ArrayList<>());
        }
        if (!responseDocumentos.isSuccess()) {
            logger.info("la lista de imagenes se encuentra vacia");
            documentosTiposAdjuntosResponse.setImagenes(new ArrayList<>());
        }
        documentosTiposAdjuntosResponse.setDocumentos(responseDocumentos.getResult());
        documentosTiposAdjuntosResponse.setImagenes(responseImagenes.getResult());
        return documentosTiposAdjuntosResponse;
    }
}
