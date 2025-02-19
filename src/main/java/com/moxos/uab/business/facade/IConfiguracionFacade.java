package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.configuration.ConfigurationResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IConfiguracionFacade {

    List<ConfigurationResponse> obtenerConfiguraciones();

    List<ListView> getPlanesPei();

    GeneralResponse saveConfiguration(ConfiguracionRequest configuracion);

    PeiResponse getPlanPei(String id);

    DetallePeriodoProgramacionRequest getGestion(String valor);

    List<ListView> getGestionesPei();
}
