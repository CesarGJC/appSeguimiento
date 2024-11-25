package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.DetallePeriodoProgramacionRequest;
import com.moxos.uab.domain.dto.request.DetallePeriodoProgramacion.ParametroPeiRequest;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.response.DetallePeriodoProgramacion.DetallePeriodoProgramacionResponse;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DetallePeriodoProgramacionController {

    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public DetallePeriodoProgramacionController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/detalle-periodo-programacion/index/{id}")
    public String index(@PathVariable Integer id, @ModelAttribute("model") ParametrosPaginacionBusquedaRequest<ParametroPeiRequest> model, Model modelo) {
        model.setOption(new ParametroPeiRequest(id, 0));
        var planPei = politicasIndicadoresAreasFacade.getPeiModel(id);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "DESCRIPCION"));
        opcionesElementos.add(new SelectListItemDto("1", "PLAN PEI"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        modelo.addAttribute("plan", planPei);
        return "DetallePeriodoProgramacion/Index";
    }

    @GetMapping("/detalle-periodo-programacion/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<ParametroPeiRequest> model, @ModelAttribute("parametros") ParametroPeiRequest parametros, Model modelo) {
        model.setOption(parametros);
        var paginacion = politicasIndicadoresAreasFacade.getDetallePeriodoProgramacion(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "DetallePeriodoProgramacion/_Listar";
    }

    @GetMapping("/detalle-periodo-programacion/update")
    public String editar(@ModelAttribute("model") DetallePeriodoProgramacionRequest model, Model modelo) {
        DetallePeriodoProgramacionRequest detallePeriodoProgramacionRequest = politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(model.getId_detalle_periodos_programacion());
        modelo.addAttribute("model", detallePeriodoProgramacionRequest);
        return "DetallePeriodoProgramacion/_Update";
    }

    @PostMapping("/detalle-periodo-programacion/update")
    public String editar(@ModelAttribute("model") @Valid DetallePeriodoProgramacionRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "DetallePeriodoProgramacion/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<DetallePeriodoProgramacionResponse> response = politicasIndicadoresAreasFacade.saveDetallePeriodoProgramacion(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "DetallePeriodoProgramacion/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "DetallePeriodoProgramacion/_Update";
        }
    }

    @GetMapping("/detalle-periodo-programacion/new")
    public String nuevo(@ModelAttribute("model") DetallePeriodoProgramacionRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        return "DetallePeriodoProgramacion/_New";
    }

    @PostMapping("/detalle-periodo-programacion/new")
    public String nuevo(@ModelAttribute("model") @Valid DetallePeriodoProgramacionRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "DetallePeriodoProgramacion/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<DetallePeriodoProgramacionResponse> response = politicasIndicadoresAreasFacade.saveDetallePeriodoProgramacion(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "DetallePeriodoProgramacion/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "DetallePeriodoProgramacion/_New";
        }
    }

    @GetMapping("/detalle-periodo-programacion/delete")
    public String eliminar(@ModelAttribute("model") DetallePeriodoProgramacionRequest model, Model modelo) {
        DetallePeriodoProgramacionRequest detallePeriodoProgramacionRequest = politicasIndicadoresAreasFacade.getDetallePeriodoProgramacionModel(model.getId_detalle_periodos_programacion());
        modelo.addAttribute("model", detallePeriodoProgramacionRequest);
        return "DetallePeriodoProgramacion/_Delete";
    }

    @PostMapping("/detalle-periodo-programacion/delete")
    public String eliminar(@ModelAttribute("model") @Valid DetallePeriodoProgramacionRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteDetallePeriodoProgramacion(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "DetallePeriodoProgramacion/_Notificacion";
    }


}
