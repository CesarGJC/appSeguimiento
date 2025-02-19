package com.moxos.uab.presentation.controller.permisos;

import com.moxos.uab.business.facade.IPermisosFacade;
import com.moxos.uab.domain.dto.request.permisos.PermisosRequest;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.permisos.PermisosResponse;
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
public class PermisosController {
//    private final IPermisosFacade permisosFacade;
//
//    public PermisosController(IPermisosFacade permisosFacade) {
//        this.permisosFacade = permisosFacade;
//    }
//
//    private Clientes getUsuario() {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
//    }
//
//    @GetMapping("/permisos/new")
//    public String nuevo(@ModelAttribute("model") PermisosRequest model, Model modelo) {
//        modelo.addAttribute("model", model);
//        modelo.addAttribute("permiso", permisosFacade.getPermisosModel(model.getId_permisos()));
//        return "Permisos/_New";
//    }
//
//    @PostMapping("/acciones-estrategicas/new")
//    public String nuevo(@ModelAttribute("model") @Valid PermisosRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            modelo.addAttribute("model", model);
//            modelo.addAttribute("permiso", permisosFacade.getPermisosModel(model.getId_permisos()));
//            return "Permisos/_New";
//        }
//        model.setUlt_usuario(getUsuario().getId_usuario());
//        Response<PermisosResponse> response = permisosFacade.savePermisos(model);
//        if (response.isSuccess()) {
//            redirectAttributes.addAttribute("id", model.getId_permisos());
//            return "redirect:/programacion/detalle/formulario";//Ojooo
//        } else {
//            result.addError(new FieldError("model", "objetivos_estrategicos", response.getMessage()));
//            modelo.addAttribute("model", model);
//            modelo.addAttribute("permiso", permisosFacade.getPermisosModel(model.getId_permisos()));
//            return "Permisos/_New";
//        }
//    }

}
