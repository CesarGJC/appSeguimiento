package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IConfigurationService;
import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.configuration.ConfigurationResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.Configuracion;
import com.moxos.uab.persistence.die.ConfigurationDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigurationServiceImpl implements IConfigurationService {
    private final ConfigurationDao configurationDao;
    private final ModelMapper modelMapper;

    public ConfigurationServiceImpl(ConfigurationDao configurationDao, ModelMapper modelMapper) {
        this.configurationDao = configurationDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<List<ListView>> getConfiguraciones() {
        try {
            List<ListView> listViews = new ArrayList<>();
            for (var item : configurationDao.getConfiguracion())
                listViews.add(new ListView(String.valueOf(item.getId_configuracion()), String.format("%s(%s)", item.getClave(), item.getValor())));
            return new Response<>(true, "", listViews);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public ConfigurationResponse getConfigurationPorClave(String clave) {
        return modelMapper.map(configurationDao.getConfiguracionPorClave(clave), ConfigurationResponse.class);
    }

    @Override
    public GeneralResponse saveConfiguration(ConfiguracionRequest configuracion) {
        try {
            configurationDao.saveConfiguracion(modelMapper.map(configuracion, Configuracion.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<String> getConfigurationByValor(String clave) {
        try {
            String response = configurationDao.getValor(clave);
            return new Response<>(true, "", response);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), "");
        }
    }
}
