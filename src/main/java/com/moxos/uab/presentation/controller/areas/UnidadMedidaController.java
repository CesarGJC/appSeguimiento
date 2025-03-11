package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.unidadmedida.UnidadMedidaRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.unidadmedida.UnidadMedidaResponse;
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
import java.util.Objects;

@Controller
public class UnidadMedidaController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;
    ;

    public UnidadMedidaController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/unidad-medida/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "DESCRIPCION"));
        opcionesElementos.add(new SelectListItemDto("1", "ABREVIACION"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "UnidadMedida/Index";
    }

    @GetMapping("/unidad-medida/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getUnidadMedida(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "UnidadMedida/_Listar";
    }

    @GetMapping("/unidad-medida/update")
    public String editar(@ModelAttribute("model") UnidadMedidaRequest model, Model modelo) {
        UnidadMedidaRequest UnidadMedidaRequest = politicasIndicadoresAreasFacade.getUnidadMedidaModel(model.getId_unidad_medida());
        modelo.addAttribute("model", UnidadMedidaRequest);
        return "UnidadMedida/_Update";
    }

    @PostMapping("/unidad-medida/update")
    public String editar(@ModelAttribute("model") @Valid UnidadMedidaRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "UnidadMedida/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<UnidadMedidaResponse> response = politicasIndicadoresAreasFacade.saveUnidadMedida(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "UnidadMedida/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "UnidadMedida/_Update";
        }
    }

    @GetMapping("/unidad-medida/new")
    public String nuevo(@ModelAttribute("model") UnidadMedidaRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("display", politicasIndicadoresAreasFacade.getUnidadesMedidas());
        return "UnidadMedida/_New";
    }

    @PostMapping("/unidad-medida/new")
    public String nuevo(@ModelAttribute("model") @Valid UnidadMedidaRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("display", politicasIndicadoresAreasFacade.getUnidadesMedidas());
            return "UnidadMedida/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<UnidadMedidaResponse> response = politicasIndicadoresAreasFacade.saveUnidadMedida(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return Objects.equals(politicasIndicadoresAreasFacade.getUnidadesMedidas(), "item") ? "UnidadMedida/_FilasCombo" : "UnidadMedida/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "UnidadMedida/_New";
        }
    }

    @GetMapping("/unidad-medida/delete")
    public String eliminar(@ModelAttribute("model") UnidadMedidaRequest model, Model modelo) {
        UnidadMedidaRequest UnidadMedidaRequest = politicasIndicadoresAreasFacade.getUnidadMedidaModel(model.getId_unidad_medida());
        modelo.addAttribute("model", UnidadMedidaRequest);
        return "UnidadMedida/_Delete";
    }

    @PostMapping("/unidad-medida/delete")
    public String eliminar(@ModelAttribute("model") @Valid UnidadMedidaRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteUnidadMedida(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "UnidadMedida/_Notificacion";
    }

    @GetMapping("/unidad-medida/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getUnidadMedida(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "UnidadMedida/_Listar";
    }


}
