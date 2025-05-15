package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.accioncortoplazopoa.AccionCortoPlazoPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accioncortoplazopoa.AccionCortoPlazoPoaResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccionCortoPlazoPoaController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public AccionCortoPlazoPoaController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/accion-corto-plazo/new")
    public String nuevo(@ModelAttribute("model") AccionCortoPlazoPoaRequest model, Model modelo) {
        modelo.addAttribute("model",model);
        modelo.addAttribute("objetivo",politicasIndicadoresAreasFacade.getObjetivoGestionPoaModel(model.getId_objetivos_gestion_poa()));
        return "AccionesCortoPlazoPoa/_New";
    }
    @PostMapping("/accion-corto-plazo/new")
    public String nuevo(@ModelAttribute("model") @Valid AccionCortoPlazoPoaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model",model);
            modelo.addAttribute("objetivo",politicasIndicadoresAreasFacade.getObjetivoGestionPoaModel(model.getId_objetivos_gestion_poa()));
            return "AccionesCortoPlazoPoa/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AccionCortoPlazoPoaResponse> response = politicasIndicadoresAreasFacade.saveAccionCortoPlazoPoa(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            redirectAttributes.addAttribute("id_programa", response.getResult().getId_programa());
            redirectAttributes.addAttribute("id_departamento", response.getResult().getId_departamento());
            redirectAttributes.addAttribute("id_detalle_periodos_programacion",response.getResult().getId_detalle_periodos_programacion());
            return "redirect:/poa/formulario";
        } else {
            result.addError(new FieldError("model", "//accion-corto-plazo", response.getMessage()));
            modelo.addAttribute("model",model);
            modelo.addAttribute("objetivo",politicasIndicadoresAreasFacade.getObjetivoGestionPoaModel(model.getId_objetivos_gestion_poa()));
            return "AccionesCortoPlazoPoa/_New";
        }
    }
    @GetMapping("/accion-corto-plazo/update")
    public String editar(@ModelAttribute("model") AccionCortoPlazoPoaRequest model, Model modelo) {
        AccionCortoPlazoPoaRequest accionCortoPlazoPoaRequest = politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(model.getId_accion_corto_plazo_poa());
        accionCortoPlazoPoaRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", accionCortoPlazoPoaRequest);
        modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivoGestionPoaModel(accionCortoPlazoPoaRequest.getId_objetivos_gestion_poa()));
        return "AccionesCortoPlazoPoa/_Update";
    }

    @PostMapping("/accion-corto-plazo/update")
    public String editar(@ModelAttribute("model") @Valid AccionCortoPlazoPoaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_gestion_poa()));
            return "AccionesCortoPlazoPoa/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AccionCortoPlazoPoaResponse> response = politicasIndicadoresAreasFacade.saveAccionCortoPlazoPoa(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            redirectAttributes.addAttribute("id_programa", response.getResult().getId_programa());
            redirectAttributes.addAttribute("id_departamento", response.getResult().getId_departamento());
            redirectAttributes.addAttribute("id_detalle_periodos_programacion",response.getResult().getId_detalle_periodos_programacion());
            return "redirect:/poa/formulario";
        } else {
            result.addError(new FieldError("model", "accion-corto-plazo", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_gestion_poa()));
            return "AccionesCortoPlazoPoa/_Update";
        }
    }

    @GetMapping("/accion-corto-plazo/delete")
    public String eliminar(@ModelAttribute("model") AccionCortoPlazoPoaRequest model, Model modelo) {
        AccionCortoPlazoPoaRequest accionCortoPlazoPoaRequest = politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(model.getId_accion_corto_plazo_poa());
        accionCortoPlazoPoaRequest.setId_programa(model.getId_programa());
        accionCortoPlazoPoaRequest.setId_departamento(model.getId_departamento());
        accionCortoPlazoPoaRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", accionCortoPlazoPoaRequest);
        return "AccionesCortoPlazoPoa/_Delete";
    }

    @PostMapping("/accion-corto-plazo/delete")
    public String eliminar(@ModelAttribute("model") AccionCortoPlazoPoaRequest model, Model modelo, RedirectAttributes redirectAttributes) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteAccionCortoPlazoPoa(model);
        if (!response.isSuccess()) {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
            return "AccionesCortoPlazoPoa/_Notificacion";
        }
        redirectAttributes.addAttribute("id", model.getId_formulario());
        redirectAttributes.addAttribute("id_programa", model.getId_programa());
        redirectAttributes.addAttribute("id_departamento", model.getId_departamento());
        redirectAttributes.addAttribute("id_detalle_periodos_programacion", model.getId_detalle_periodos_programacion());
        return "redirect:/poa/formulario";
    }
}
