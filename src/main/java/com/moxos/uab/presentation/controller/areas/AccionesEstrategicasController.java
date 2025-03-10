package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.accionestrategica.AccionEstrategicaRequest;
import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.accionestrategica.AccionEstrategicaResponse;
import com.moxos.uab.domain.dto.response.objetivosestrategicos.ObjetivosEstrategicosResponse;
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
public class AccionesEstrategicasController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public AccionesEstrategicasController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/acciones-estrategicas/new")
    public String nuevo(@ModelAttribute("model") AccionEstrategicaRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_estrategicos()));
        return "AccionesEstrategicas/_New";
    }

    @PostMapping("/acciones-estrategicas/new")
    public String nuevo(@ModelAttribute("model") @Valid AccionEstrategicaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_estrategicos()));
            return "AccionesEstrategicas/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AccionEstrategicaResponse> response = politicasIndicadoresAreasFacade.saveAccionesEstrategicas(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            return "redirect:/programacion/detalle/formulario";
        } else {
            result.addError(new FieldError("model", "objetivos_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_estrategicos()));
            return "AccionesEstrategicas/_New";
        }
    }
    @GetMapping("/acciones-estrategicas/update")
    public String editar(@ModelAttribute("model") AccionEstrategicaRequest model, Model modelo) {
        AccionEstrategicaRequest accionesEstrategicosRequest = politicasIndicadoresAreasFacade.getAccionesEstrategicasModel(model.getId_acciones_estrategica());
        accionesEstrategicosRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", accionesEstrategicosRequest);
        modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(accionesEstrategicosRequest.getId_objetivos_estrategicos()));
        return "AccionesEstrategicas/_Update";
    }

    @PostMapping("/acciones-estrategicas/update")
    public String editar(@ModelAttribute("model") @Valid AccionEstrategicaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_estrategicos()));
            return "AccionesEstrategicas/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AccionEstrategicaResponse> response = politicasIndicadoresAreasFacade.saveAccionesEstrategicas(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            return "redirect:/programacion/detalle/formulario";
        } else {
            result.addError(new FieldError("model", "objetivos_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_estrategicos()));
            return "AccionesEstrategicas/_Update";
        }
    }
    @GetMapping("/acciones-estrategicas/delete")
    public String eliminar(@ModelAttribute("model") AccionEstrategicaRequest model, Model modelo) {
        AccionEstrategicaRequest accionesEstrategicosRequest = politicasIndicadoresAreasFacade.getAccionesEstrategicasModel(model.getId_acciones_estrategica());
        accionesEstrategicosRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", accionesEstrategicosRequest);
        return "AccionesEstrategicas/_Delete";
    }

    @PostMapping("/acciones-estrategicas/delete")
    public String eliminar(@ModelAttribute("model") AccionEstrategicaRequest model, Model modelo, RedirectAttributes redirectAttributes) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteAccionesEstrategicas(model);
        if (!response.isSuccess()) {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
            return "AccionesEstrategicas/_Notificacion";
        }
        redirectAttributes.addAttribute("id", model.getId_formulario());
        return "redirect:/programacion/detalle/formulario";
    }
}
