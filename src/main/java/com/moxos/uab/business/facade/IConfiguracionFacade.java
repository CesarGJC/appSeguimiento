package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.detallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.request.permisos.AsignarPermisosRequest;
import com.moxos.uab.domain.dto.request.permisos.HabilitarPermisoRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.configuration.ConfiguracionParametrosResponse;
import com.moxos.uab.domain.dto.response.configuration.ConfiguracionPlanInstitucionalResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.dto.response.permisos.PermisosUnidadResponse;
import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface IConfiguracionFacade {

    ConfiguracionParametrosResponse obtenerConfiguraciones();

    List<ListView> getPlanesPei();

    GeneralResponse saveConfiguration(ConfiguracionRequest configuracion);

    PeiResponse getPlanPei(String id);

    DetallePeriodoProgramacionRequest getGestion(String valor);

    List<ListView> getGestionesPei();

    String getValorConfiguracionPorClave(String valor);

    List<PermisosUnidadResponse> getPermisosUnidades(int idTipoUnidad, int idFormulario);

    List<ListView> getListarUnidades(Integer idTipoUnidad);

    void savePermisosUnidad(AsignarPermisosRequest model);

    GeneralResponse habilitarPermiso(HabilitarPermisoRequest model);

    List<FormularioResponse> getFormulariosPorPlan(int idPlanPei);

    Response<ConfiguracionPlanInstitucionalResponse> obtenerConfiguracionesPlan();

    List<FormularioResponse> getFormulariosPorPlan(Integer idUnidad, Integer idTipoUnidad, Integer idPlanPei);
}
