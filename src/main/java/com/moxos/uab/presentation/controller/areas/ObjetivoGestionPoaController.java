package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.*;
import com.moxos.uab.domain.dto.request.objetivogestionpoa.ObjetivoGestionPoaRequest;
import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.objetivogestionpoa.ObjetivoGestionPoaResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ObjetivoGestionPoaController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;
    private final IConfiguracionFacade configuracionFacade;
    private final IActividadesProgramadasFacade actividadesFacade;

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/objetivo-gestion-poa/new")
    public String nuevo(@ModelAttribute("model") ObjetivoGestionPoaRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("detalle", politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(model.getId_detalle_periodos_programacion()));
        modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(model.getId_departamento(), model.getId_programa()));
        return "ObjetivosGestionPoa/_New";
    }

    @PostMapping("/objetivo-gestion-poa/new")
    public String nuevo(@ModelAttribute("model") @Valid ObjetivoGestionPoaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("detalle", politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(model.getId_detalle_periodos_programacion()));
            return "ObjetivosGestionPoa/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<ObjetivoGestionPoaResponse> response = politicasIndicadoresAreasFacade.saveObjetivoGestionPoa(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            redirectAttributes.addAttribute("id_programa", model.getId_programa());
            redirectAttributes.addAttribute("id_departamento", model.getId_departamento());
            redirectAttributes.addAttribute("id_detalle_periodos_programacion", model.getId_detalle_periodos_programacion());
            return "redirect:/poa/formulario";
        } else {
            result.addError(new FieldError("model", "/objetivo-gestion-poa", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("detalle", politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(model.getId_detalle_periodos_programacion()));
            return "ObjetivosGestionPoa/_New";
        }
    }

    @GetMapping("/objetivo-gestion-poa/update")
    public String editar(@ModelAttribute("model") ObjetivoGestionPoaRequest model, Model modelo) {
        ObjetivoGestionPoaRequest objetivoGestionPoaRequest = politicasIndicadoresAreasFacade.getObjetivoGestionPoaModel(model.getId_objetivos_gestion_poa());
        objetivoGestionPoaRequest.setId_formulario(model.getId_formulario());
        objetivoGestionPoaRequest.setId_programa(model.getId_programa());
        objetivoGestionPoaRequest.setId_departamento(model.getId_departamento());
        modelo.addAttribute("model", objetivoGestionPoaRequest);
        modelo.addAttribute("detalle", politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(objetivoGestionPoaRequest.getId_detalle_periodos_programacion()));
        modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(objetivoGestionPoaRequest.getId_departamento(), objetivoGestionPoaRequest.getId_programa()));
        return "ObjetivosGestionPoa/_Update";
    }

    @PostMapping("/objetivo-gestion-poa/update")
    public String editar(@ModelAttribute("model") @Valid ObjetivoGestionPoaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("detalle", politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(model.getId_detalle_periodos_programacion()));
            return "ObjetivosGestionPoa/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<ObjetivoGestionPoaResponse> response = politicasIndicadoresAreasFacade.saveObjetivoGestionPoa(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            redirectAttributes.addAttribute("id_programa", model.getId_programa());
            redirectAttributes.addAttribute("id_departamento", model.getId_departamento());
            redirectAttributes.addAttribute("id_detalle_periodos_programacion", model.getId_detalle_periodos_programacion());
            return "redirect:/poa/formulario";
        } else {
            result.addError(new FieldError("model", "objetivos_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("detalle", politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(model.getId_detalle_periodos_programacion()));
            return "ObjetivosGestionPoa/_Update";
        }
    }

    @GetMapping("/objetivo-gestion-poa/delete")
    public String eliminar(@ModelAttribute("model") ObjetivoGestionPoaRequest model, Model modelo) {
        ObjetivoGestionPoaRequest objetivoGestionPoaRequest = politicasIndicadoresAreasFacade.getObjetivoGestionPoaModel(model.getId_objetivos_gestion_poa());
        objetivoGestionPoaRequest.setId_programa(model.getId_programa());
        objetivoGestionPoaRequest.setId_departamento(model.getId_departamento());
        objetivoGestionPoaRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", objetivoGestionPoaRequest);
        return "ObjetivosGestionPoa/_Delete";
    }

    @PostMapping("/objetivo-gestion-poa/delete")
    public String eliminar(@ModelAttribute("model") ObjetivoGestionPoaRequest model, Model modelo, RedirectAttributes redirectAttributes) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteObjetivoGestionPoa(model);
        if (!response.isSuccess()) {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
            return "ObjetivosGestionPoa/_Notificacion";
        }
        redirectAttributes.addAttribute("id", model.getId_formulario());
        redirectAttributes.addAttribute("id_programa", model.getId_programa());
        redirectAttributes.addAttribute("id_departamento", model.getId_departamento());
        redirectAttributes.addAttribute("id_detalle_periodos_programacion", model.getId_detalle_periodos_programacion());
        return "redirect:/poa/formulario";
    }
}
