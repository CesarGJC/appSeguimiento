package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IAdjuntarService;
import com.moxos.uab.domain.dto.request.adjuntos.AdjuntosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.adjuntos.AdjuntosResponse;
import com.moxos.uab.domain.entity.die.Adjuntos;
import com.moxos.uab.persistence.die.AdjuntosDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdjuntarServiceImpl implements IAdjuntarService {
    private final AdjuntosDao adjuntosDao;
    private final ModelMapper modelMapper;

    public AdjuntarServiceImpl(AdjuntosDao adjuntosDao, ModelMapper modelMapper) {
        this.adjuntosDao = adjuntosDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<AdjuntosRequest> getAdjuntoPorId(Integer id) {
        try {
            var response = modelMapper.map(adjuntosDao.getByid(id), AdjuntosRequest.class);
            return new Response<>(true, "", response);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    @Override
    public Response<AdjuntosResponse> getAdjuntoDetallePorId(Integer id) {
        try {
            var response = modelMapper.map(adjuntosDao.getByDetalleid(id), AdjuntosResponse.class);
            return new Response<>(true, "", response);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    @Override
    public Response<List<AdjuntosResponse>> getListaAdjuntoPorActvidades(Integer idOperaciones, String tipoDocumento) {
        try {
            Adjuntos adjuntos = new Adjuntos();
            adjuntos.setId_operaciones(idOperaciones);
            adjuntos.setTipo_documento(tipoDocumento);
            var response = adjuntosDao.getListarDocumentosAdjuntosPoActividad(adjuntos)
                    .stream().map(p -> modelMapper.map(p, AdjuntosResponse.class)).collect(Collectors.toList());
            return new Response<>(true, "", response);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse addAdjunto(AdjuntosRequest adjunto) {
        try {
            Integer idResult = adjuntosDao.saveAdjuntos(modelMapper.map(adjunto, Adjuntos.class));
            return new GeneralResponse(idResult != 0, idResult == 0 ? "No se grabo correctamente la informacion" : "");
        } catch (Exception ex) {
            return new GeneralResponse(false, ex.getMessage());
        }
    }

    @Override
    public Response<AdjuntosResponse> getAdjuntoDetalleDocumentoPorIdActividad(Integer id) {
        try {
            var response = modelMapper.map(adjuntosDao.getAdjuntoPorIdActividad(id), AdjuntosResponse.class);
            return new Response<>(true, "", response);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }
}
