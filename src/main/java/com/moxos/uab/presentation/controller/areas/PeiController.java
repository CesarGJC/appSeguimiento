package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PeiController {

    private IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public PeiController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/pei/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "GESTION"));
        opcionesElementos.add(new SelectListItemDto("1", "DESCRIPCION"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "Pei/Index";
    }

    @GetMapping("/pei/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getPei(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "Pei/_Listar";
    }

    @GetMapping("/pei/update")
    public String editar(@ModelAttribute("model") PeiRequest model, Model modelo) {
        PeiRequest pieRequest = politicasIndicadoresAreasFacade.getPeiModel(model.getId_plan_pei());
        modelo.addAttribute("model", pieRequest);
        return "Pei/_Update";
    }

    @PostMapping("/pei/update")
    public String editar(@ModelAttribute("model") @Valid PeiRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "Pei/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<PeiResponse> response = politicasIndicadoresAreasFacade.savePei(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "Pei/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "Pei/_Update";
        }
    }

    @GetMapping("/pei/new")
    public String nuevo(@ModelAttribute("model") PeiRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        return "Pei/_New";
    }

    @PostMapping("/pei/new")
    public String nuevo(@ModelAttribute("model") @Valid PeiRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "Pei/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<PeiResponse> response = politicasIndicadoresAreasFacade.savePei(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "Pei/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "Pei/_New";
        }
    }

    @GetMapping("/pei/delete")
    public String eliminar(@ModelAttribute("model") PeiRequest model, Model modelo) {
        PeiRequest peiRequest = politicasIndicadoresAreasFacade.getPeiModel(model.getId_plan_pei());
        modelo.addAttribute("model", peiRequest);
        return "Pei/_Delete";
    }

    @PostMapping("/pei/delete")
    public String eliminar(@ModelAttribute("model") @Valid PeiRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deletePei(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "Pei/_Notificacion";
    }

    @GetMapping("/pei/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getPei(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "Pei/_Listar";
    }
}
