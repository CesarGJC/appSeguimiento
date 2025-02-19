package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IConfiguracionFacade;
import com.moxos.uab.business.service.IConfigurationService;
import com.moxos.uab.business.service.IDetallePeriodoProgramacionService;
import com.moxos.uab.business.service.IPeiService;
import com.moxos.uab.common.enums.ConfiguracionEnums;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.configuration.ConfigurationResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfiguracionFacadeImpl implements IConfiguracionFacade {

    private final IConfigurationService configurationService;
    private final IPeiService peiService;
    private final IDetallePeriodoProgramacionService detallePeriodoProgramacionService;

    public ConfiguracionFacadeImpl(IConfigurationService configurationService, IPeiService peiService, IDetallePeriodoProgramacionService detallePeriodoProgramacionService) {
        this.configurationService = configurationService;
        this.peiService = peiService;
        this.detallePeriodoProgramacionService = detallePeriodoProgramacionService;
    }

    @Override
    public List<ListView> getPlanesPei() {
        return peiService.listPlanEstrategicaInstitucional().getResult();
    }

    @Override
    public List<ListView> getGestionesPei() {
        ConfigurationResponse configuracionPlanPei = configurationService.getConfigurationPorClave(ConfiguracionEnums.PLAN_PEI);
        return detallePeriodoProgramacionService.getPeriodosGestionesByid(Integer.parseInt(configuracionPlanPei.getValor())).getResult();
    }

    @Override
    public GeneralResponse saveConfiguration(ConfiguracionRequest configuracion) {
        return configurationService.saveConfiguration(configuracion);
    }

    @Override
    public List<ConfigurationResponse> obtenerConfiguraciones() {
        List<ConfigurationResponse> configuraciones = new ArrayList<>();
        ConfigurationResponse configuracionPlanPei = configurationService.getConfigurationPorClave(ConfiguracionEnums.PLAN_PEI);
        var planPei = peiService.getByid(Integer.parseInt(configuracionPlanPei.getValor()));
        configuracionPlanPei.setDescripcion(planPei.getResult().getDescripcion());
        configuracionPlanPei.setUrl("/configuracion/cambiar-plan");
        ConfigurationResponse configuracionGestion = configurationService.getConfigurationPorClave(ConfiguracionEnums.GESTION);
        var detallePeriodoGestion = detallePeriodoProgramacionService.getByid(Integer.parseInt(configuracionGestion.getValor()));
        configuracionGestion.setDescripcion(detallePeriodoGestion.getResult().getDescripcion());
        configuracionGestion.setUrl("/configuracion/cambiar-gestion");
        configuraciones.add(configuracionPlanPei);
        configuraciones.add(configuracionGestion);
        return configuraciones;
    }

    @Override
    public PeiResponse getPlanPei(String valor) {
        return peiService.getByid(Integer.parseInt(valor)).getResult();
    }

    @Override
    public DetallePeriodoProgramacionRequest getGestion(String valor) {
        return detallePeriodoProgramacionService.getByid(Integer.parseInt(valor)).getResult();
    }
}

