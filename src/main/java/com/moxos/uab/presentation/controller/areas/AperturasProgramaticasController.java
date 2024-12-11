package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.aperturasprogramaticas.AperturasProgramaticasResponse;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.aperturasprogramaticas.AperturasProgramaticasRequest;
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
public class AperturasProgramaticasController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public AperturasProgramaticasController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }
    @GetMapping("/aperturas-programaticas/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "DESCRIPCION"));
        opcionesElementos.add(new SelectListItemDto("1", "CODIGO"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "AperturasProgramaticas/Index";
    }

    @GetMapping("/aperturas-programaticas/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getAperturasProgramaticas(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "AperturasProgramaticas/_Listar";
    }

    @GetMapping("/aperturas-programaticas/update")
    public String editar(@ModelAttribute("model") AperturasProgramaticasRequest model, Model modelo) {
        AperturasProgramaticasRequest aperturasProgramaticasRequest = politicasIndicadoresAreasFacade.getAperturasProgramaticasModel(model.getId_apertura_programatica());
        modelo.addAttribute("model", aperturasProgramaticasRequest);
        return "AperturasProgramaticas/_Update";
    }

    @PostMapping("/aperturas-programaticas/update")
    public String editar(@ModelAttribute("model") @Valid AperturasProgramaticasRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "AperturasProgramaticas/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AperturasProgramaticasResponse> response = politicasIndicadoresAreasFacade.saveAperturasProgramaticas(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "AperturasProgramaticas/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "AperturasProgramaticas/_Update";
        }
    }

    @GetMapping("/aperturas-programaticas/new")
    public String nuevo(@ModelAttribute("model") AperturasProgramaticasRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        return "AperturasProgramaticas/_New";
    }

    @PostMapping("/aperturas-programaticas/new")
    public String nuevo(@ModelAttribute("model") @Valid AperturasProgramaticasRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "AperturasProgramaticas/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<AperturasProgramaticasResponse> response = politicasIndicadoresAreasFacade.saveAperturasProgramaticas(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "AperturasProgramaticas/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "AperturasProgramaticas/_New";
        }
    }

    @GetMapping("/aperturas-programaticas/delete")
    public String eliminar(@ModelAttribute("model") AperturasProgramaticasRequest model, Model modelo) {
        AperturasProgramaticasRequest AperturasProgramaticasRequest = politicasIndicadoresAreasFacade.getAperturasProgramaticasModel(model.getId_apertura_programatica());
        modelo.addAttribute("model", AperturasProgramaticasRequest);
        return "AperturasProgramaticas/_Delete";
    }

    @PostMapping("/aperturas-programaticas/delete")
    public String eliminar(@ModelAttribute("model") @Valid AperturasProgramaticasRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteAperturasProgramaticas(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "AperturasProgramaticas/_Notificacion";
    }

    @GetMapping("/aperturas-programaticas/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getAperturasProgramaticas(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "AperturasProgramaticas/_Listar";
    }

}
