package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IConfigurationService;
import com.moxos.uab.domain.dto.response.configuration.ConfigurationResponse;
import com.moxos.uab.persistence.die.ConfigurationDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements IConfigurationService {
    private final ConfigurationDao configurationDao;
    private final ModelMapper modelMapper;

    public ConfigurationServiceImpl(ConfigurationDao configurationDao, ModelMapper modelMapper) {
        this.configurationDao = configurationDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public ConfigurationResponse getConfiguracionActual() {
        return modelMapper.map(configurationDao.getConfiguracionActual(), ConfigurationResponse.class);
    }
}
