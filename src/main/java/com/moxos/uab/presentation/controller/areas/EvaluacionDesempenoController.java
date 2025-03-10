package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.response.Response;
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
public class EvaluacionDesempenoController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public EvaluacionDesempenoController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }
//Solo es la estructura
    @GetMapping("/evaluacion/new")
    public String nuevo(@ModelAttribute("model") ObjetivosEstrategicosRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(model.getId_area_estrategica()));
        return "Evaluacion/_New";
    }

    @PostMapping("/evaluacion/new")
    public String nuevo(@ModelAttribute("model") @Valid ObjetivosEstrategicosRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(model.getId_area_estrategica()));
            return "Evaluacion/_New";
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
            return "Evaluacion/_New";
        }
    }
}
