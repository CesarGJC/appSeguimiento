package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.areasestrategicas.AreasEstrategicasRequest;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.areasestrategicas.AreaEstrategicaResponse;
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
public class AreasEstrategicasController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;


    public AreasEstrategicasController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/areas-estrategicas/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "DESCRIPCION"));
        opcionesElementos.add(new SelectListItemDto("1", "GESTION"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "AreasEstrategicas/Index";
    }

    @GetMapping("/areas-estrategicas/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getAreaEstrategicas(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "AreasEstrategicas/_Listar";
    }

    @GetMapping("/areas-estrategicas/update")
    public String editar(@ModelAttribute("model") AreasEstrategicasRequest model, Model modelo) {
        AreasEstrategicasRequest areasEstrategicasRequest = politicasIndicadoresAreasFacade.getAreaEstrategicasModel(model.getId_area_estrategica());
        modelo.addAttribute("model", areasEstrategicasRequest);
        return "AreasEstrategicas/_Update";
    }

    @PostMapping("/areas-estrategicas/update")
    public String editar(@ModelAttribute("model") @Valid AreasEstrategicasRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "AreasEstrategicas/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AreaEstrategicaResponse> response = politicasIndicadoresAreasFacade.saveAreaEstrategica(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "AreasEstrategicas/_Filas";
        } else {
            result.addError(new FieldError("model", "area_estrategica", response.getMessage()));
            modelo.addAttribute("model", model);
            return "AreasEstrategicas/_Update";
        }
    }

    @GetMapping("/areas-estrategicas/new")
    public String nuevo(@ModelAttribute("model") AreasEstrategicasRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        return "AreasEstrategicas/_New";
    }

    @PostMapping("/areas-estrategicas/new")
    public String nuevo(@ModelAttribute("model") @Valid AreasEstrategicasRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "AreasEstrategicas/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AreaEstrategicaResponse> response = politicasIndicadoresAreasFacade.saveAreaEstrategica(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "AreasEstrategicas/_Filas";
        } else {
            result.addError(new FieldError("model", "area_estrategica", response.getMessage()));
            modelo.addAttribute("model", model);
            return "AreasEstrategicas/_New";
        }
    }

    @GetMapping("/areas-estrategicas/delete")
    public String eliminar(@ModelAttribute("model") AreasEstrategicasRequest model, Model modelo) {
        AreasEstrategicasRequest areasEstrategicasRequest = politicasIndicadoresAreasFacade.getAreaEstrategicasModel(model.getId_area_estrategica());
        modelo.addAttribute("model", areasEstrategicasRequest);
        return "AreasEstrategicas/_Delete";
    }

    @PostMapping("/areas-estrategicas/delete")
    public String eliminar(@ModelAttribute("model") @Valid AreasEstrategicasRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteAreaEstrategica(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "AreasEstrategicas/_Notificacion";
    }
    @GetMapping("/areas-estrategicas/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo){
        var paginacion = politicasIndicadoresAreasFacade.getAreaEstrategicas(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "AreasEstrategicas/_Listar";
    }
}
