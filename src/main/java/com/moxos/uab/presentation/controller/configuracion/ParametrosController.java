package com.moxos.uab.presentation.controller.configuracion;

import com.moxos.uab.business.facade.IActividadesProgramadasFacade;
import com.moxos.uab.business.facade.IConfiguracionFacade;
import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.common.enums.ConfiguracionEnums;
import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.request.configuracion.ParametrosUnidadesRequest;
import com.moxos.uab.domain.dto.request.permisos.AsignarPermisosRequest;
import com.moxos.uab.domain.dto.request.permisos.HabilitarPermisoRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.configuration.ConfiguracionPlanInstitucionalResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/configuracion")
public class ParametrosController {
    private final IConfiguracionFacade configuracionFacade;
    private final IActividadesProgramadasFacade actividadesProgramadasFacade;
    private final IFormularioProgramacionFacade formularioProgramacionFacade;

    public ParametrosController(IConfiguracionFacade configuracionFacade, IActividadesProgramadasFacade actividadesProgramadasFacade, IFormularioProgramacionFacade formularioProgramacionFacade) {
        this.configuracionFacade = configuracionFacade;
        this.actividadesProgramadasFacade = actividadesProgramadasFacade;
        this.formularioProgramacionFacade = formularioProgramacionFacade;
    }

    @GetMapping("/index")
    public String index(Model modelo) {
        var response = configuracionFacade.obtenerConfiguraciones();
        var planes = configuracionFacade.getPlanesPei();
        var planGestion = configuracionFacade.getGestionesPei();
        modelo.addAttribute("gestiones", planGestion);
        modelo.addAttribute("planes", planes);
        modelo.addAttribute("configuraciones", response);
        return "Configuracion/Parametros/Index";
    }

    @PostMapping("/cambiar-plan")
    @ResponseBody
    public Response<ConfiguracionPlanInstitucionalResponse> cambiarPlan(@RequestBody ConfiguracionRequest model) {
        model.setClave("id_plan_pei");
        GeneralResponse response = configuracionFacade.saveConfiguration(model);
        if (!response.isSuccess()) {
            return new Response<>(response.isSuccess(), response.getMessage(), null);
        }
        return configuracionFacade.obtenerConfiguracionesPlan();
    }

    @PostMapping("/cambiar-gestion")
    @ResponseBody
    public GeneralResponse cambiarGestion(@RequestBody ConfiguracionRequest model) {
        model.setClave("id_periodo_gestion");
        return configuracionFacade.saveConfiguration(model);
    }

    @GetMapping("/permisos")
    public String permisos(@ModelAttribute("model") ParametrosUnidadesRequest model, Model modelo) {
        modelo.addAttribute("tiposUnidades", getTiposUnidades());
        modelo.addAttribute("model", model);
        return "Configuracion/Parametros/SeleccionarTipoUnidad";
    }

    @PostMapping("/permisos")
    public String permisos(@ModelAttribute("model") @Valid ParametrosUnidadesRequest model, BindingResult result, RedirectAttributes redirectAttributes, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("tiposUnidades", getTiposUnidades());
            modelo.addAttribute("model", model);
            return "Configuracion/Parametros/SeleccionarTipoUnidad";
        }
        redirectAttributes.addAttribute("id", model.getId());
        redirectAttributes.addAttribute("tipoUnidad", model.getTipoUnidad());
        return "redirect:/configuracion/permisos-unidad";
    }

    @GetMapping("/permisos-unidad")
    public String permisosUnidad(@ModelAttribute("model") ParametrosUnidadesRequest model, Model modelo) {
        modelo.addAttribute("unidadesPermisos", configuracionFacade.getPermisosUnidades(model.getTipoUnidad(), model.getId()));
        modelo.addAttribute("model", model);
        return "Configuracion/Parametros/UnidadesPermisos";
    }

    @GetMapping("/seleccionar-unidad")
    public String seleccionarUnidades(@ModelAttribute("model") ParametrosUnidadesRequest model, Model modelo) {
        AsignarPermisosRequest request = new AsignarPermisosRequest();
        request.setId_tipo_unidad(model.getTipoUnidad());
        request.setId_formulario(model.getId());
        modelo.addAttribute("unidadesPermisos", configuracionFacade.getListarUnidades(model.getTipoUnidad()));
        modelo.addAttribute("model", model);
        modelo.addAttribute("asignar", request);
        return "Configuracion/Parametros/AsignarPermisos";
    }

    @PostMapping("/asignar")
    public String asignarUnidades(@ModelAttribute("model") AsignarPermisosRequest model, RedirectAttributes redirectAttributes) {
        configuracionFacade.savePermisosUnidad(model);
        redirectAttributes.addAttribute("id", model.getId_formulario());
        redirectAttributes.addAttribute("tipoUnidad", model.getId_tipo_unidad());
        return "redirect:/configuracion/permisos-unidad";
    }

    @PostMapping("/habilitar")
    @ResponseBody
    public GeneralResponse habilitar(@RequestBody HabilitarPermisoRequest model) {
        return configuracionFacade.habilitarPermiso(model);
    }

    private List<ListView> getTiposUnidades() {
        List<ListView> tiposUnidades = new ArrayList<>();
        tiposUnidades.add(new ListView("1", "Unidades administrativas"));
        tiposUnidades.add(new ListView("2", "Unidades academicas"));
        return tiposUnidades;
    }

    private void setAtributte(Model modelo, String clave, String valor) {
        switch (clave) {
            case ConfiguracionEnums.PLAN_PEI:
                var responsePlan = configuracionFacade.getPlanPei(valor);
                modelo.addAttribute("valor", responsePlan.getDescripcion());
                break;

            case ConfiguracionEnums.GESTION:
                var responseGestion = configuracionFacade.getGestion(valor);
                modelo.addAttribute("valor", responseGestion.getDescripcion());
                break;
        }
    }
}
