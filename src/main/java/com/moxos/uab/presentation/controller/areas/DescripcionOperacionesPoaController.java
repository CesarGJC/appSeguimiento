package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IFormularioPoaFacade;
import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.descripcionoperacionespoa.DescripcionOperacionesPoaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.descripcionoperacionespoa.DescripcionOperacionesPoaResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class DescripcionOperacionesPoaController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;
    private final IFormularioPoaFacade formularioPoaFacade;

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/descripcion-operaciones/new")
    public String nuevo(@ModelAttribute("model") DescripcionOperacionesPoaRequest model, Model modelo) {
        var formularioProgramacionPoa = formularioPoaFacade.getFormularioProgramacionPoaCabecera(model.getId_formulario());
        modelo.addAttribute("model", model);
        modelo.addAttribute("formulario", formularioProgramacionPoa);
        modelo.addAttribute("descripcion", politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(model.getId_accion_corto_plazo_poa()));
        return "DescripcionOperacionesPoa/_New";
    }

    @PostMapping("/descripcion-operaciones/new")
    public String nuevo(@ModelAttribute("model") @Valid DescripcionOperacionesPoaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            var formularioProgramacionPoa = formularioPoaFacade.getFormularioProgramacionPoaCabecera(model.getId_formulario());
            modelo.addAttribute("model", model);
            modelo.addAttribute("formulario", formularioProgramacionPoa);
            modelo.addAttribute("descripcion", politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(model.getId_accion_corto_plazo_poa()));
            return "DescripcionOperacionesPoa/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<DescripcionOperacionesPoaResponse> response = politicasIndicadoresAreasFacade.saveDescripcionOperaciones(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            redirectAttributes.addAttribute("id_programa", response.getResult().getId_programa());
            redirectAttributes.addAttribute("id_departamento", response.getResult().getId_departamento());
            redirectAttributes.addAttribute("id_detalle_periodos_programacion", response.getResult().getId_detalle_periodos_programacion());
            return "redirect:/poa/formulario";
        } else {
            result.addError(new FieldError("model", "//descripcion-operaciones", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("descripcion", politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(model.getId_accion_corto_plazo_poa()));
            return "DescripcionOperacionesPoa/_New";
        }
    }

    @GetMapping("/descripcion-operaciones/update")
    public String editar(@ModelAttribute("model") DescripcionOperacionesPoaRequest model, Model modelo) {
        var formularioProgramacionPoa = formularioPoaFacade.getFormularioProgramacionPoaCabecera(model.getId_formulario());
        DescripcionOperacionesPoaRequest descripcionOperacionesPoaRequest = politicasIndicadoresAreasFacade.getDescripcionOperacionesPoaModel(model.getId_descripcion_operaciones_poa());
        descripcionOperacionesPoaRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("formulario", formularioProgramacionPoa);
        modelo.addAttribute("model", descripcionOperacionesPoaRequest);
        modelo.addAttribute("descripcion", politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(descripcionOperacionesPoaRequest.getId_accion_corto_plazo_poa()));
        return "DescripcionOperacionesPoa/_Update";
    }

    @PostMapping("/descripcion-operaciones/update")
    public String editar(@ModelAttribute("model") @Valid DescripcionOperacionesPoaRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            var formularioProgramacionPoa = formularioPoaFacade.getFormularioProgramacionPoaCabecera(model.getId_formulario());
            modelo.addAttribute("formulario", formularioProgramacionPoa);
            modelo.addAttribute("model", model);
            modelo.addAttribute("descripcion", politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(model.getId_accion_corto_plazo_poa()));
            return "DescripcionOperacionesPoa/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<DescripcionOperacionesPoaResponse> response = politicasIndicadoresAreasFacade.saveDescripcionOperaciones(model);
        if (response.isSuccess()) {
            redirectAttributes.addAttribute("id", model.getId_formulario());
            redirectAttributes.addAttribute("id_programa", response.getResult().getId_programa());
            redirectAttributes.addAttribute("id_departamento", response.getResult().getId_departamento());
            redirectAttributes.addAttribute("id_detalle_periodos_programacion", response.getResult().getId_detalle_periodos_programacion());
            return "redirect:/poa/formulario";
        } else {
            result.addError(new FieldError("model", "descripcion-operaciones", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("descripcion", politicasIndicadoresAreasFacade.getAccionCortoPlazoPoaModel(model.getId_accion_corto_plazo_poa()));
            return "DescripcionOperacionesPoa/_Update";
        }
    }

    @GetMapping("/descripcion-operaciones/delete")
    public String eliminar(@ModelAttribute("model") DescripcionOperacionesPoaRequest model, Model modelo) {
        DescripcionOperacionesPoaRequest descripcionOperacionesPoaRequest = politicasIndicadoresAreasFacade.getDescripcionOperacionesPoaModel(model.getId_descripcion_operaciones_poa());
        descripcionOperacionesPoaRequest.setId_formulario(model.getId_formulario());
        modelo.addAttribute("model", descripcionOperacionesPoaRequest);
        return "DescripcionOperacionesPoa/_Delete";
    }

    @PostMapping("/descripcion-operaciones/delete")
    public String eliminar(@ModelAttribute("model") DescripcionOperacionesPoaRequest model, Model modelo, RedirectAttributes redirectAttributes) {
        var response = politicasIndicadoresAreasFacade.deleteDescripcionOperacionesPoa(model);
        if (!response.isSuccess()) {

            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
            return "DescripcionOperacionesPoa/_Notificacion";
        }
        redirectAttributes.addAttribute("id", model.getId_formulario());
        redirectAttributes.addAttribute("id_programa", response.getResult().getId_programa());
        redirectAttributes.addAttribute("id_departamento", response.getResult().getId_departamento());
        redirectAttributes.addAttribute("id_detalle_periodos_programacion", response.getResult().getId_detalle_periodos_programacion());
        return "redirect:/poa/formulario";
    }
}
