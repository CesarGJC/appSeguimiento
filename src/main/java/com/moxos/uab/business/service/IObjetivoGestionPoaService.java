package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.objetivogestionpoa.ObjetivoGestionPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaFormularioResponse;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaResponse;

import java.util.List;

public interface IObjetivoGestionPoaService {
    Response<Integer> saveObjetivosGestionPoa(ObjetivoGestionPoaRequest objetivoGestionPoaRequest);

    GeneralResponse deleteObjetivosGestionPoa(ObjetivoGestionPoaRequest objetivoGestionPoaRequest);

    Response<ObjetivoGestionPoaResponse> getByid(int id_objetivos_gestion_poa);

    Response<List<ObjetivoGestionPoaFormularioResponse>> getObjetivosGestionPoa(ObjetivoGestionPoaFormularioResponse objetivoGestionPoaFormularioResponse);
}
