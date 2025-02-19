package com.moxos.uab.business.service;


import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.configuration.ConfigurationResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IConfigurationService {

    Response<List<ListView>> getConfiguraciones();

    ConfigurationResponse getConfigurationPorClave(String clave);

    GeneralResponse saveConfiguration(ConfiguracionRequest configuracion);

    Response<String> getConfigurationByValor(String clave);
}
