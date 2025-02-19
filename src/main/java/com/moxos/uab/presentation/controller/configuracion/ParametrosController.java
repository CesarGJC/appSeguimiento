package com.moxos.uab.presentation.controller.configuracion;

import com.moxos.uab.business.facade.IActividadesProgramadasFacade;
import com.moxos.uab.business.facade.IConfiguracionFacade;
import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.common.enums.ConfiguracionEnums;
import com.moxos.uab.domain.dto.request.configuracion.ConfiguracionRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        modelo.addAttribute("configuraciones", response);
        int idPlanPei = Integer.parseInt(actividadesProgramadasFacade.getValorConfiguracionPorClave("id_plan_pei"));
        List<FormularioResponse> formularioResponsesList = actividadesProgramadasFacade.getFormulariosPorPlan(idPlanPei);
        modelo.addAttribute("formularioResponsesList", formularioResponsesList);
        return "Configuracion/Parametros/Index";
    }

    @GetMapping("/cambiar-plan")
    public String cambiarPlan(@ModelAttribute("model") ConfiguracionRequest model, Model modelo) {
        var response = configuracionFacade.getPlanesPei();
        modelo.addAttribute("model", model);
        modelo.addAttribute("planes", response);
        return "Configuracion/Parametros/_CambiarPlan";
    }
    @GetMapping("/cambiar-gestion")
    public String cambiarGestion(@ModelAttribute("model") ConfiguracionRequest model, Model modelo) {
        var response = configuracionFacade.getGestionesPei();
        modelo.addAttribute("model", model);
        modelo.addAttribute("planes", response);
        return "Configuracion/Parametros/_CambiarGestion";
    }
    @PostMapping("/cambiar")
    public String cambiar(@ModelAttribute("model") ConfiguracionRequest model, Model modelo) {
        GeneralResponse response = configuracionFacade.saveConfiguration(model);
        if (!response.isSuccess()) {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
            return "Configuracion/Parametros/_Notificacion";
        }
        setAtributte(modelo, model.getClave(), model.getValor());
        return "Configuracion/Parametros/_Resultado";
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
