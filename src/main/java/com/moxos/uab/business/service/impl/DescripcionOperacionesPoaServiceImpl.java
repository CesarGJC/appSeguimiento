package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IDescripcionOperacionesPoaService;
import com.moxos.uab.common.enums.TrimestreEnum;
import com.moxos.uab.domain.dto.request.descripcionoperacionespoa.DescripcionOperacionesPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import com.moxos.uab.domain.dto.response.descripcionoperacionespoa.DescripcionOperacionesPoaResponse;
import com.moxos.uab.domain.entity.die.DescripcionOperacionesPoa;
import com.moxos.uab.domain.entity.die.Trimestre;
import com.moxos.uab.persistence.die.DescripcionOperacionesPoaDao;
import com.moxos.uab.persistence.die.TrimestreDao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DescripcionOperacionesPoaServiceImpl implements IDescripcionOperacionesPoaService {
    private final ModelMapper modelMapper;
    private final DescripcionOperacionesPoaDao descripcionOperacionesPoaDao;
    private final TrimestreDao trimestreDao;

    @Override
    public Response<Integer> saveDescripcionOperacionesPoa(DescripcionOperacionesPoaRequest descripcionOperacionesPoaRequest) {
        try {
            var value = modelMapper.map(descripcionOperacionesPoaRequest, DescripcionOperacionesPoa.class);
            Integer result = descripcionOperacionesPoaDao.saveDescripcionOperacionesPoa(value);
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse deleteDescripcionOperacionesPoa(DescripcionOperacionesPoaRequest descripcionOperacionesPoaRequest) {
        try {
            descripcionOperacionesPoaDao.deleteDescripcionOperacionesPoa(modelMapper.map(descripcionOperacionesPoaRequest, DescripcionOperacionesPoa.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<DescripcionOperacionesPoaResponse> getByid(int id_descripcion_operaciones_poa) {
        try {
            List<Trimestre> trimestres = trimestreDao.getTrimestre(id_descripcion_operaciones_poa);
            DescripcionOperacionesPoaResponse descripcionOperacionesPoaResponse = modelMapper.map(descripcionOperacionesPoaDao.getByid(id_descripcion_operaciones_poa), DescripcionOperacionesPoaResponse.class);
            descripcionOperacionesPoaResponse.setPrimerTrimestre(trimestres.stream().filter(p -> p.getTrimestre().equals(TrimestreEnum.PRIMER_TRIMESTRE)).findFirst().orElse(new Trimestre()).getProgramado());
            descripcionOperacionesPoaResponse.setSegundoTrimestre(trimestres.stream().filter(p -> p.getTrimestre().equals(TrimestreEnum.SEGUNDO_TRIMESTRE)).findFirst().orElse(new Trimestre()).getProgramado());
            descripcionOperacionesPoaResponse.setTercerTrimestre(trimestres.stream().filter(p -> p.getTrimestre().equals(TrimestreEnum.TERCER_TRIMESTRE)).findFirst().orElse(new Trimestre()).getProgramado());
            descripcionOperacionesPoaResponse.setCuartoTrimestre(trimestres.stream().filter(p -> p.getTrimestre().equals(TrimestreEnum.CUARTO_TRIMESTRE)).findFirst().orElse(new Trimestre()).getProgramado());
            return new Response<>(true, "", descripcionOperacionesPoaResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public GeneralResponse esPorcentaje(int id_descripcion_operaciones_poa) {
        try {
            var response = descripcionOperacionesPoaDao.esPorcentaje(id_descripcion_operaciones_poa);
            return new GeneralResponse(response, response ? "" : "no es porcentaje");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

}
