package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.entity.siiga.Clientes;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class ResultadoGestionController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public ResultadoGestionController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

//    @GetMapping("/resultados-gestion/new")
//    public String nuevo(@ModelAttribute("model") ResultadosGestionRequest model, Model modelo) {
//        modelo.addAttribute("model", model);
//        modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(model.getId_area_estrategica()));
//        return "ResultadosGestion/_New";
//    }
//
//    @PostMapping("/resultados-gestion/new")
//    public String nuevo(@ModelAttribute("model") @Valid ObjetivosEstrategicosRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            modelo.addAttribute("model", model);
//            modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreaEstrategicasDetalle(model.getId_area_estrategica()));
//            return "ResultadosGestion/_New";
//        }
//        model.setUlt_usuario(getUsuario().getId_usuario());
//        Response<ObjetivosEstrategicosResponse> response = politicasIndicadoresAreasFacade.saveObjetivosEstrategicos(model);
//        if (response.isSuccess()) {
//            redirectAttributes.addAttribute("id", model.getId_formulario());
//            return "redirect:/programacion/detalle/formulario";
//        } else {
//            result.addError(new FieldError("model", "objetivos_estrategicos", response.getMessage()));
//            modelo.addAttribute("model", model);
//            modelo.addAttribute("area", politicasIndicadoresAreasFacade.getAreasEstrategica());
//            return "ResultadosGestion/_New";
//        }
//    }
}
