package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.politicasdesarrollo.PoliticasDesarrolloRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.politicasdesarrollo.PoliticasDesarrolloResponse;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class PoliticasDesarrolloController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public PoliticasDesarrolloController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }
    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }
    @GetMapping("/politicas-desarrollo/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "AREAS ESTRATEGICAS"));
        opcionesElementos.add(new SelectListItemDto("1", "POLITICAS DE DESARROLLO"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "PoliticasDesarrollo/Index";
    }
    @GetMapping("/politicas-desarrollo/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getPoliticasDesarrollo(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "PoliticasDesarrollo/_Listar";
    }
    @GetMapping("/politicas-desarrollo/update")
    public String editar(@ModelAttribute("model") PoliticasDesarrolloRequest model, Model modelo) {
        PoliticasDesarrolloRequest politicasDesarrolloRequest = politicasIndicadoresAreasFacade.getPoliticasDesarrolloModel(model.getId_politica_desarrollo());
        politicasDesarrolloRequest.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
        modelo.addAttribute("model", politicasDesarrolloRequest);
        return "PoliticasDesarrollo/_Update";
    }

    @PostMapping("/politicas-desarrollo/update")
    public String editar(@ModelAttribute("model") @Valid PoliticasDesarrolloRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            model.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
            modelo.addAttribute("model", model);
            return "PoliticasDesarrollo/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<PoliticasDesarrolloResponse> response = politicasIndicadoresAreasFacade.savePoliticasDesarrollo(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "PoliticasDesarrollo/_Filas";
        } else {
            result.addError(new FieldError("model", "politica_desarrollo", response.getMessage()));
            modelo.addAttribute("model", model);
            return "PoliticasDesarrollo/_Update";
        }
    }

    @GetMapping("/politicas-desarrollo/new")
    public String nuevo(@ModelAttribute("model") PoliticasDesarrolloRequest model, Model modelo) {
        model.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
        modelo.addAttribute("model", model);
        return "PoliticasDesarrollo/_New";
    }

    @PostMapping("/politicas-desarrollo/new")
    public String nuevo(@ModelAttribute("model") @Valid PoliticasDesarrolloRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            model.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
            modelo.addAttribute("model", model);
            return "PoliticasDesarrollo/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<PoliticasDesarrolloResponse> response = politicasIndicadoresAreasFacade.savePoliticasDesarrollo(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "PoliticasDesarrollo/_Filas";
        } else {
            result.addError(new FieldError("model", "politica_desarrollo", response.getMessage()));
            modelo.addAttribute("model", model);
            return "PoliticasDesarrollo/_New";
        }
    }

    @GetMapping("/politicas-desarrollo/delete")
    public String eliminar(@ModelAttribute("model") PoliticasDesarrolloRequest model, Model modelo) {
        PoliticasDesarrolloRequest PoliticasDesarrolloRequest = politicasIndicadoresAreasFacade.getPoliticasDesarrolloModel(model.getId_politica_desarrollo());
        modelo.addAttribute("model", PoliticasDesarrolloRequest);
        return "PoliticasDesarrollo/_Delete";
    }

    @PostMapping("/politicas-desarrollo/delete")
    public String eliminar(@ModelAttribute("model") @Valid PoliticasDesarrolloRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deletePoliticasDesarrollo(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "PoliticasDesarrollo/_Notificacion";
    }
    @GetMapping("/politicas-desarrollo/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo){
        var paginacion = politicasIndicadoresAreasFacade.getPoliticasDesarrollo(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "PoliticasDesarrollo/_Listar";
    }
}
