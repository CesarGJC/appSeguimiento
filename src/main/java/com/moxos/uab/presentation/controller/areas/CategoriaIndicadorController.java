package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.categoriaindicador.CategoriaIndicadorRequest;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.pei.PeiRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.categoriaindicador.CategoriaIndicadorResponse;
import com.moxos.uab.domain.dto.response.pei.PeiResponse;
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
public class CategoriaIndicadorController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    private CategoriaIndicadorController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/categoria-indicador/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "DESCRIPCION"));
        opcionesElementos.add(new SelectListItemDto("1", "ABREVIACION"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "CategoriaIndicador/Index";
    }

    @GetMapping("/categoria-indicador/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getCategoriaIndicador(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "CategoriaIndicador/_Listar";
    }

    @GetMapping("/categoria-indicador/update")
    public String editar(@ModelAttribute("model") CategoriaIndicadorRequest model, Model modelo) {
        CategoriaIndicadorRequest categoriaIndicadorRequest = politicasIndicadoresAreasFacade.getCategoriaIndicadorModel(model.getId_categoria());
        modelo.addAttribute("model", categoriaIndicadorRequest);
        return "CategoriaIndicador/_Update";
    }

    @PostMapping("/categoria-indicador/update")
    public String editar(@ModelAttribute("model") @Valid CategoriaIndicadorRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "CategoriaIndicador/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<CategoriaIndicadorResponse> response = politicasIndicadoresAreasFacade.saveCategoriaIndicador(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "CategoriaIndicador/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "CategoriaIndicador/_Update";
        }
    }

    @GetMapping("/categoria-indicador/new")
    public String nuevo(@ModelAttribute("model") CategoriaIndicadorRequest model, @ModelAttribute("display") String display, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("display", display);
        return "CategoriaIndicador/_New";
    }

    @PostMapping("/categoria-indicador/new")
    public String nuevo(@ModelAttribute("model") @Valid CategoriaIndicadorRequest model, @ModelAttribute("display") String display, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("display", display);
            return "CategoriaIndicador/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<CategoriaIndicadorResponse> response = politicasIndicadoresAreasFacade.saveCategoriaIndicador(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return Objects.equals(display, "item") ? "CategoriaIndicador/_FilasCombo" : "CategoriaIndicador/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "CategoriaIndicador/_New";
        }
    }

    @GetMapping("/categoria-indicador/delete")
    public String eliminar(@ModelAttribute("model") CategoriaIndicadorRequest model, Model modelo) {
        CategoriaIndicadorRequest categoriaIndicadorRequest = politicasIndicadoresAreasFacade.getCategoriaIndicadorModel(model.getId_categoria());
        modelo.addAttribute("model", categoriaIndicadorRequest);
        return "CategoriaIndicador/_Delete";
    }

    @PostMapping("/categoria-indicador/delete")
    public String eliminar(@ModelAttribute("model") @Valid CategoriaIndicadorRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteCategoriaIndicador(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "CategoriaIndicador/_Notificacion";
    }

    @GetMapping("/categoria-indicador/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getCategoriaIndicador(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "CategoriaIndicador/_Listar";
    }
}

