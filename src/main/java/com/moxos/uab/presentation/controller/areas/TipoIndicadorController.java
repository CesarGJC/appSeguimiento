package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.tipoindicador.TipoIndicadorRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.tipoindicador.TipoIndicadorResponse;
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
public class TipoIndicadorController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public TipoIndicadorController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/tipo-indicador/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "DESCRIPCION"));
        opcionesElementos.add(new SelectListItemDto("1", "ABREVIACION"));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "TipoIndicador/Index";
    }

    @GetMapping("/tipo-indicador/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getTipoIndicador(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "TipoIndicador/_Listar";
    }

    @GetMapping("/tipo-indicador/update")
    public String editar(@ModelAttribute("model") TipoIndicadorRequest model, Model modelo) {
        TipoIndicadorRequest TipoIndicadorRequest = politicasIndicadoresAreasFacade.getTipoIndicadorModel(model.getId_tipo_indicador());
        modelo.addAttribute("model", TipoIndicadorRequest);
        return "TipoIndicador/_Update";
    }

    @PostMapping("/tipo-indicador/update")
    public String editar(@ModelAttribute("model") @Valid TipoIndicadorRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "TipoIndicador/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<TipoIndicadorResponse> response = politicasIndicadoresAreasFacade.saveTipoIndicador(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "TipoIndicador/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            return "TipoIndicador/_Update";
        }
    }

    @GetMapping("/tipo-indicador/new")
    public String nuevo(@ModelAttribute("model") TipoIndicadorRequest model, @ModelAttribute("display") String display, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("display", display);
        return "TipoIndicador/_New";
    }

    @PostMapping("/tipo-indicador/new")
    public String nuevo(@ModelAttribute("model") @Valid TipoIndicadorRequest model, @ModelAttribute("display") String display, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            modelo.addAttribute("display", display);
            return "TipoIndicador/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<TipoIndicadorResponse> response = politicasIndicadoresAreasFacade.saveTipoIndicador(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return Objects.equals(display, "item") ? "TipoIndicador/_FilasCombo" : "TipoIndicador/_Filas";
        } else {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("display", display);
            return "TipoIndicador/_New";
        }
    }

    @GetMapping("/tipo-indicador/delete")
    public String eliminar(@ModelAttribute("model") TipoIndicadorRequest model, Model modelo) {
        TipoIndicadorRequest TipoIndicadorRequest = politicasIndicadoresAreasFacade.getTipoIndicadorModel(model.getId_tipo_indicador());
        modelo.addAttribute("model", TipoIndicadorRequest);
        return "TipoIndicador/_Delete";
    }

    @PostMapping("/tipo-indicador/delete")
    public String eliminar(@ModelAttribute("model") @Valid TipoIndicadorRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteTipoIndicador(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "TipoIndicador/_Notificacion";
    }

    @GetMapping("/tipo-indicador/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getTipoIndicador(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "TipoIndicador/_Listar";
    }
}
