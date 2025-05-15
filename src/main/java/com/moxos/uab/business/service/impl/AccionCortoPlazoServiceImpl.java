package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IAccionCortoPlazoPoaService;
import com.moxos.uab.domain.dto.request.accioncortoplazopoa.AccionCortoPlazoPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;
import com.moxos.uab.domain.entity.die.AccionCortoPlazoPoa;
import com.moxos.uab.persistence.die.AccionCortoPlazoPoaDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccionCortoPlazoServiceImpl implements IAccionCortoPlazoPoaService {
    private final ModelMapper modelMapper;
    private final AccionCortoPlazoPoaDao accionCortoPlazoPoaDao;

    public AccionCortoPlazoServiceImpl(ModelMapper modelMapper, AccionCortoPlazoPoaDao accionCortoPlazoPoaDao) {
        this.modelMapper = modelMapper;
        this.accionCortoPlazoPoaDao = accionCortoPlazoPoaDao;
    }

    @Override
    public Response<Integer> saveAccionCortoPlazoPoa(AccionCortoPlazoPoaRequest accionCortoPlazoPoaRequest) {
        try {
            var value = modelMapper.map(accionCortoPlazoPoaRequest, AccionCortoPlazoPoa.class);
            Integer result = accionCortoPlazoPoaDao.saveAccionCortoPlazoPoa(value);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse deleteAccionCortoPlazoPoa(AccionCortoPlazoPoaRequest accionCortoPlazoPoaRequest) {
        try {
            accionCortoPlazoPoaDao.deleteAccionCortoPlazoPoa(modelMapper.map(accionCortoPlazoPoaRequest, AccionCortoPlazoPoa.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<AccionCortoPlazoPoaResponse> getByid(int id_accion_corto_plazo_poa) {
        try {
            AccionCortoPlazoPoaResponse accionCortoPlazoPoaResponse = modelMapper.map(accionCortoPlazoPoaDao.getByid(id_accion_corto_plazo_poa), AccionCortoPlazoPoaResponse.class);
            return new Response<>(true, "", accionCortoPlazoPoaResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<AccionCortoPlazoPoaResponse>> getDetalleByid(int id_accion_corto_plazo_poa) {
        try {
            List<AccionCortoPlazoPoaResponse> listViews = new ArrayList<>();
            for (var item : accionCortoPlazoPoaDao.getAccionesCortoPlazo(id_accion_corto_plazo_poa))
                listViews.add(new AccionCortoPlazoPoaResponse(item.getId_accion_corto_plazo_poa(), item.getAccion_corto_plazo_poa()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<List<AccionCortoPlazoPoaResponse>> getAccionCortoPlazo(int id_accion_corto_plazo_poa) {
        try {
            List<AccionCortoPlazoPoaResponse> listViews = new ArrayList<>();
            for (var item : accionCortoPlazoPoaDao.getAccionesCortoPlazo(id_accion_corto_plazo_poa))
                listViews.add(new AccionCortoPlazoPoaResponse(item.getId_accion_corto_plazo_poa(), item.getAccion_corto_plazo_poa()));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }
}
