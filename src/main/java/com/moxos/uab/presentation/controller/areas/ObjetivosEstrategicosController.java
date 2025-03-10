package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
import com.moxos.uab.domain.dto.response.objetivosestrategicos.ObjetivosEstrategicosResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ObjetivosEstrategicosController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public ObjetivosEstrategicosController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/objetivos-estrategicos/new")
    public String nuevo(@ModelAttribute("model") ObjetivosEstrategicosRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(model.getId_area_estrategica()));
        return "ObjetivosEstrategicos/_New";
    }

    @PostMapping("/objetivos-estrategicos/new")
    public String nuevo(@ModelAttribute("model") @Valid ObjetivosEstrategicosRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(model.getId_area_estrategica()));
            return "ObjetivosEstrategicos/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<ObjetivosEstrategicosResponse> response = politicasIndicadoresAreasFacade.saveObjetivosEstrategicos(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            return "redirect:/programacion/detalle/formulario";
        } else {
            result.addError(new FieldError("model", "objetivos_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreasEstrategica());
            return "ObjetivosEstrategicos/_New";
        }
    }

    @GetMapping("/objetivos-estrategicos/update")
    public String editar(@ModelAttribute("model") ObjetivosEstrategicosRequest model, Model modelo) {
        ObjetivosEstrategicosRequest objetivosEstrategicosRequest = politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_estrategicos());
        objetivosEstrategicosRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", objetivosEstrategicosRequest);
        modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(objetivosEstrategicosRequest.getId_area_estrategica()));
        return "ObjetivosEstrategicos/_Update";
    }

    @PostMapping("/objetivos-estrategicos/update")
    public String editar(@ModelAttribute("model") @Valid ObjetivosEstrategicosRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(model.getId_area_estrategica()));
            return "ObjetivosEstrategicos/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<ObjetivosEstrategicosResponse> response = politicasIndicadoresAreasFacade.saveObjetivosEstrategicos(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            return "redirect:/programacion/detalle/formulario";
        } else {
            result.addError(new FieldError("model", "objetivos_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreasEstrategica());
            return "ObjetivosEstrategicos/_Update";
        }
    }

    @GetMapping("/objetivos-estrategicos/delete")
    public String eliminar(@ModelAttribute("model") ObjetivosEstrategicosRequest model, Model modelo) {
        ObjetivosEstrategicosRequest objetivosEstrategicosRequest = politicasIndicadoresAreasFacade.getObjetivosEstrategicosModel(model.getId_objetivos_estrategicos());
        objetivosEstrategicosRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", objetivosEstrategicosRequest);
        return "ObjetivosEstrategicos/_Delete";
    }

    @PostMapping("/objetivos-estrategicos/delete")
    public String eliminar(@ModelAttribute("model") ObjetivosEstrategicosRequest model, Model modelo, RedirectAttributes redirectAttributes) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteObjetivosEstrategicos(model);
        if (!response.isSuccess()) {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
            return "ObjetivosEstrategicos/_Notificacion";
        }
        redirectAttributes.addAttribute("id", model.getId_formulario());
        return "redirect:/programacion/detalle/formulario";
    }
}
