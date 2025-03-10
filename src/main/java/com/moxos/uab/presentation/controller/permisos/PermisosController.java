package com.moxos.uab.presentation.controller.permisos;

import org.springframework.stereotype.Controller;

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
