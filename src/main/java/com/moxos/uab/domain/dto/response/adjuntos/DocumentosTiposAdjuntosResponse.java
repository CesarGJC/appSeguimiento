package com.moxos.uab.domain.dto.response.adjuntos;

import lombok.Data;

import java.util.List;

@Data
public class DocumentosTiposAdjuntosResponse {
    private List<AdjuntosResponse> imagenes;
    private List<AdjuntosResponse> documentos;

}
