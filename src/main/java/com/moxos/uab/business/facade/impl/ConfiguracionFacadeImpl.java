package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IConfiguracionFacade;
import com.moxos.uab.business.service.*;
import com.moxos.uab.common.enums.ConfiguracionEnums;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.request.permisos.AsignarPermisosRequest;
import com.moxos.uab.domain.dto.request.permisos.HabilitarPermisoRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.configuration.ConfiguracionParametrosResponse;
import com.moxos.uab.domain.dto.response.configuration.ConfiguracionPlanInstitucionalResponse;
import com.moxos.uab.domain.dto.response.configuration.ConfigurationResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.permisos.PermisosUnidadResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfiguracionFacadeImpl implements IConfiguracionFacade {

    private final IConfigurationService configurationService;
    private final IPeiService peiService;
    private final IDetallePeriodoProgramacionService detallePeriodoProgramacionService;
    private final IPermisosService permisosService;
    private final IDepartamentoService departamentoService;
    private final IProgramaSevice programaSevice;
    private final IFormularioService formularioService;
    private static final Logger logger = LogManager.getLogger(ConfiguracionFacadeImpl.class);

    public ConfiguracionFacadeImpl(IConfigurationService configurationService, IPeiService peiService, IDetallePeriodoProgramacionService detallePeriodoProgramacionService, IPermisosService permisosService, IDepartamentoService departamentoService, IProgramaSevice programaSevice, IFormularioService formularioService) {
        this.configurationService = configurationService;
        this.peiService = peiService;
        this.detallePeriodoProgramacionService = detallePeriodoProgramacionService;
        this.permisosService = permisosService;
        this.departamentoService = departamentoService;
        this.programaSevice = programaSevice;
        this.formularioService = formularioService;
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
    public List<PermisosUnidadResponse> getPermisosUnidades(int idTipoUnidad, int idFormulario) {
        var response = permisosService.getListaUnidadesAdministrativas(idTipoUnidad, idFormulario);
        if (!response.isSuccess()) {
            logger.info(response.getMessage());
            return List.of();
        }
        return response.getResult();
    }

    @Override
    public List<ListView> getListarUnidades(Integer idTipoUnidad) {
        var response = idTipoUnidad == 1 ? departamentoService.getListaDepartamentos() : programaSevice.getListaProgramas();
        if (!response.isSuccess()) {
            logger.info(response.getMessage());
            return List.of();
        }
        return response.getResult();
    }

    @Override
    public void savePermisosUnidad(AsignarPermisosRequest model) {
        var response = permisosService.savePermisos(model);
        if (!response.isSuccess())
            logger.info(response.getMessage());
    }

    @Override
    public GeneralResponse habilitarPermiso(HabilitarPermisoRequest model) {
        return permisosService.habilitarPermisos(model.getIdPermiso(), model.getHabilitado());
    }

    @Override
    public GeneralResponse saveConfiguration(ConfiguracionRequest configuracion) {
        return configurationService.saveConfiguration(configuracion);
    }

    @Override
    public ConfiguracionParametrosResponse obtenerConfiguraciones() {
        ConfiguracionParametrosResponse configuracionParametrosResponse = new ConfiguracionParametrosResponse();

        ConfigurationResponse configuracionPlanPei = configurationService.getConfigurationPorClave(ConfiguracionEnums.PLAN_PEI);
        var planPei = peiService.getByid(Integer.parseInt(configuracionPlanPei.getValor()));
        configuracionPlanPei.setDescripcion(planPei.getResult().getDescripcion());
        configuracionParametrosResponse.setPlanInstitucional(configuracionPlanPei);

        ConfigurationResponse configuracionGestion = configurationService.getConfigurationPorClave(ConfiguracionEnums.GESTION);
        var detallePeriodoGestion = detallePeriodoProgramacionService.getByid(Integer.parseInt(configuracionGestion.getValor()));
        configuracionGestion.setDescripcion(detallePeriodoGestion.getResult().getDescripcion());
        configuracionParametrosResponse.setPeriodoGesionPlan(configuracionGestion);

        int idPlanPei = Integer.parseInt(getValorConfiguracionPorClave("id_plan_pei"));
        List<FormularioResponse> formularioResponsesList = getFormulariosPorPlan(idPlanPei);
        configuracionParametrosResponse.setFormularios(formularioResponsesList);

        return configuracionParametrosResponse;
    }

    @Override
    public Response<ConfiguracionPlanInstitucionalResponse> obtenerConfiguracionesPlan() {
        ConfiguracionPlanInstitucionalResponse configuracionParametrosResponse = new ConfiguracionPlanInstitucionalResponse();
        ConfigurationResponse configuracionGestion = configurationService.getConfigurationPorClave(ConfiguracionEnums.GESTION);
        configuracionParametrosResponse.setIdPeriodo(Integer.parseInt(configuracionGestion.getValor()));
        configuracionParametrosResponse.setPeriodosPlan(getGestionesPei());
        int idPlanPei = Integer.parseInt(getValorConfiguracionPorClave("id_plan_pei"));
        List<FormularioResponse> formularioResponsesList = getFormulariosPorPlan(idPlanPei);
        configuracionParametrosResponse.setFormularios(formularioResponsesList);
        return new Response<>(true, "", configuracionParametrosResponse);
    }

    @Override
    public PeiResponse getPlanPei(String valor) {
        return peiService.getByid(Integer.parseInt(valor)).getResult();
    }

    @Override
    public DetallePeriodoProgramacionRequest getGestion(String valor) {
        return detallePeriodoProgramacionService.getByid(Integer.parseInt(valor)).getResult();
    }

    @Override
    public List<FormularioResponse> getFormulariosPorPlan(int idPlanPei) {
        var response = formularioService.getFormularioPlan(idPlanPei);
        if (!response.isSuccess()) {
            logger.error("No se pudo obtener los resultados del formulario");
            return null;
        }
        return response.getResult();
    }

    @Override
    public List<FormularioResponse> getFormulariosPorPlan(Integer idUnidad, Integer idTipoUnidad, Integer idPlanPei) {
        var response = formularioService.getListaFormularioHabilitados(idUnidad, idTipoUnidad, idPlanPei);
        if (!response.isSuccess()) {
            logger.error("No se pudo obtener los resultados del formulario");
            return null;
        }
        return response.getResult();
    }

    @Override
    public String getValorConfiguracionPorClave(String valor) {
        var response = configurationService.getConfigurationByValor(valor);
        if (!response.isSuccess()) {
            logger.error(String.format("Error al obtener el valor de clave: %s", valor));
            return "";
        }
        return response.getResult();
    }
}

