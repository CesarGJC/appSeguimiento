package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.indicadoresestrategicos.IndicadoresEstrategicosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.indicadoresestrategicos.IndicadoresEstrategicosResponse;
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
public class IndicadoresEstrategicosController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public IndicadoresEstrategicosController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/indicadores-estrategicos/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        model.setOption(0);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "POLITICA DE DESARROLLO"));
        opcionesElementos.add(new SelectListItemDto("1", "AREAS ESTRATEGICAS"));
        opcionesElementos.add(new SelectListItemDto("2", "INDICADOR ESTRATEGICO"));

        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "IndicadoresEstrategicos/Index";
    }

    @GetMapping("/indicadores-estrategicos/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getIndicadoresEstrategicos(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());

        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "IndicadoresEstrategicos/_Listar";
    }

    @GetMapping("/indicadores-estrategicos/update")
    public String editar(@ModelAttribute("model") IndicadoresEstrategicosRequest model, Model modelo) {
        IndicadoresEstrategicosRequest indicadoresEstrategicosRequest = politicasIndicadoresAreasFacade.getIndicadoresEstrategicosModel(model.getId_indicador_estrategico());
        indicadoresEstrategicosRequest.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
        indicadoresEstrategicosRequest.setPoliticasDesarrollo(politicasIndicadoresAreasFacade.getPoliticasDesarrollo(indicadoresEstrategicosRequest.getId_area_estrategica()));
        modelo.addAttribute("model", indicadoresEstrategicosRequest);
        return "IndicadoresEstrategicos/_Update";
    }

    @PostMapping("/indicadores-estrategicos/update")
    public String editar(@ModelAttribute("model") @Valid IndicadoresEstrategicosRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            model.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
            model.setPoliticasDesarrollo(politicasIndicadoresAreasFacade.getPoliticasDesarrollo(model.getId_area_estrategica()));
            modelo.addAttribute("model", model);
            return "IndicadoresEstrategicos/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<IndicadoresEstrategicosResponse> response = politicasIndicadoresAreasFacade.saveIndicadoresEstrategicos(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "IndicadoresEstrategicos/_Filas";
        } else {
            result.addError(new FieldError("model", "indicadores_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            return "IndicadoresEstrategicos/_Update";
        }
    }

    @GetMapping("/indicadores-estrategicos/new")
    public String nuevo(@ModelAttribute("model") IndicadoresEstrategicosRequest model, Model modelo) {
        model.setId_area_estrategica(-1);
        model.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
        model.setId_politica_desarrollo(-1);
        model.setPoliticasDesarrollo(politicasIndicadoresAreasFacade.getPoliticasDesarrollo(model.getId_area_estrategica()));
        modelo.addAttribute("model", model);
        return "IndicadoresEstrategicos/_New";
    }

    @PostMapping("/indicadores-estrategicos/new")
    public String nuevo(@ModelAttribute("model") @Valid IndicadoresEstrategicosRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            model.setAreasEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas());
            model.setPoliticasDesarrollo(politicasIndicadoresAreasFacade.getPoliticasDesarrollo(model.getId_area_estrategica()));
            modelo.addAttribute("model", model);
            return "IndicadoresEstrategicos/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<IndicadoresEstrategicosResponse> response = politicasIndicadoresAreasFacade.saveIndicadoresEstrategicos(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "IndicadoresEstrategicos/_Filas";
        } else {
            result.addError(new FieldError("model", "indicadores_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            return "IndicadoresEstrategicos/_New";
        }
    }

    @GetMapping("/indicadores-estrategicos/delete")
    public String eliminar(@ModelAttribute("model") IndicadoresEstrategicosRequest model, Model modelo) {
        IndicadoresEstrategicosRequest IndicadoresEstrategicosRequest = politicasIndicadoresAreasFacade.getIndicadoresEstrategicosModel(model.getId_indicador_estrategico());
        modelo.addAttribute("model", IndicadoresEstrategicosRequest);
        return "IndicadoresEstrategicos/_Delete";
    }

    @PostMapping("/indicadores-estrategicos/delete")
    public String eliminar(@ModelAttribute("model") @Valid IndicadoresEstrategicosRequest model, BindingResult result, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteIndicadoresEstrategicos(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "IndicadoresEstrategicos/_Notificacion";
    }

    @GetMapping("/indicadores-estrategicos/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<Integer> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getIndicadoresEstrategicos(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());
        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "IndicadoresEstrategicos/_Listar";
    }
    @GetMapping("/indicadores-estrategicos/politicas")
    public String politicas(@ModelAttribute("id")Integer id,Model modelo) {
        var listaPoliticasDesarrollo=politicasIndicadoresAreasFacade.getPoliticasDesarrollo(id);
        modelo.addAttribute("politicas", listaPoliticasDesarrollo);
        return "IndicadoresEstrategicos/_option";
    }
}
