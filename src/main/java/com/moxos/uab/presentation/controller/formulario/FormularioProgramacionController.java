package com.moxos.uab.presentation.controller.formulario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.common.util.ReflectionUtils;
import com.moxos.uab.common.util.filter.FiltersUtils;
import com.moxos.uab.common.util.web.CookieFilter;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.formulario.FormularioRequest;
import com.moxos.uab.domain.dto.request.general.*;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.ResultResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/formulario")
public class FormularioProgramacionController {
    private CookieFilter<FilterRequest<FormularioFilterRequest>> cookieFilter;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final IFormularioProgramacionFacade formularioProgramacionFacade;
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FormularioProgramacionController(HttpServletRequest request, HttpServletResponse response, IFormularioProgramacionFacade formularioProgramacionFacade, IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.request = request;
        this.response = response;
        this.formularioProgramacionFacade = formularioProgramacionFacade;
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/programacion/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<FormularioFilterRequest> model, Model modelo) {
        model.setOption(new FormularioFilterRequest());
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemTypeDto("gestion", "GESTION", 0));
        opcionesElementos.add(new SelectListItemTypeDto("plan_pei", "PLAN PEI", 0));
        opcionesElementos.add(new SelectListItemTypeDto("area_estrategica", "AREA ESTRATEGICA", 0));
        opcionesElementos.add(new SelectListItemTypeDto("codigo", "CODIGO", 0));
        opcionesElementos.add(new SelectListItemTypeDto("titulo", "TITULO", 0));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        return "Formulario/Programacion/Index";
    }

    @GetMapping("/programacion/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<FilterParamsRequest> model, @ModelAttribute("filter") FilterParamsRequest filter, @ModelAttribute("cookie") boolean cookie, @CookieValue(value = "filter", required = false) String value, Model modelo) throws JsonProcessingException {
        var paginacion = formularioProgramacionFacade.getFormularioProgramacion(setCookieFilter(model, filter, cookie, value));
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());
        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "Formulario/Programacion/_Listar";
    }

    @GetMapping("/programacion/new")
    public String nuevo(@ModelAttribute("model") FormularioRequest model, Model modelo) {
        model.setId_formulario(-1);
        model.setPlanes(politicasIndicadoresAreasFacade.getPlanesEstrategicosInstitcuinales());
        model.setAreaEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas(model.getId_plan_pei()));
        model.setAperturaProgramaticas(politicasIndicadoresAreasFacade.getListaAperturasProgramaticas());
        modelo.addAttribute("model", model);
        return "Formulario/Programacion/New";
    }

    @PostMapping("/programacion/new")
    public String nuevo(@ModelAttribute("model") @Valid FormularioRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.setPlanes(politicasIndicadoresAreasFacade.getPlanesEstrategicosInstitcuinales());
            model.setAreaEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas(model.getId_plan_pei()));
            model.setAperturaProgramaticas(politicasIndicadoresAreasFacade.getListaAperturasProgramaticas());
            modelo.addAttribute("model", model);
            return "Formulario/Programacion/New";
        }
        return registrarCatalogo(model, redirectAttributes);
    }

    @GetMapping("/programacion/update")
    public String editar(@ModelAttribute("id") Integer id, Model modelo) {
        FormularioRequest formularioRequest = formularioProgramacionFacade.getFormularioProgramacionModel(id);
        formularioRequest.setPlanes(politicasIndicadoresAreasFacade.getPlanesEstrategicosInstitcuinales());
        formularioRequest.setAreaEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas(formularioRequest.getId_plan_pei()));
        formularioRequest.setAperturaProgramaticas(politicasIndicadoresAreasFacade.getListaAperturasProgramaticas());
        modelo.addAttribute("model", formularioRequest);
        return "Formulario/Programacion/Update";
    }

    @PostMapping("/programacion/update")
    public String editar(@ModelAttribute("model") @Valid FormularioRequest model, BindingResult result, RedirectAttributes redirectAttributes, Model modelo) {
        if (result.hasErrors()) {
            model.setPlanes(politicasIndicadoresAreasFacade.getPlanesEstrategicosInstitcuinales());
            model.setAreaEstrategicas(politicasIndicadoresAreasFacade.getAreasEstrategicas(model.getId_plan_pei()));
            model.setAperturaProgramaticas(politicasIndicadoresAreasFacade.getListaAperturasProgramaticas());
            modelo.addAttribute("model", model);
            return "Formulario/Programacion/Update";
        }
        return registrarCatalogo(model, redirectAttributes);
    }

    @GetMapping("/programacion/delete")
    public String eliminar(@ModelAttribute("model") FormularioRequest model, Model modelo) {
        FormularioRequest formularioRequest = formularioProgramacionFacade.getFormularioProgramacionModel(model.getId_formulario());
        modelo.addAttribute("model", formularioRequest);
        return "Formulario/Programacion/_Delete";
    }

    @PostMapping("/programacion/delete")
    public String eliminarFormulario(@ModelAttribute("model") FormularioRequest model, Model modelo) {
        GeneralResponse response = formularioProgramacionFacade.deleteFormulario(model.getId_formulario());
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "Formulario/Programacion/_Notificacion";
    }

    @GetMapping("/programacion/areas")
    public String getAreasEstrategicasPorPlan(@ModelAttribute("id") Integer id, Model modelo) {
        modelo.addAttribute("areasEstrategicas", politicasIndicadoresAreasFacade.getAreasEstrategicas(id));
        return "Formulario/Programacion/_Option";
    }


    private String registrarCatalogo(FormularioRequest model, RedirectAttributes redirectAttributes) {
        model.setUlt_usuario(getUsuario().getId_usuario());
        var response = formularioProgramacionFacade.saveFormularioProgramacion(model);
        if (response.isSuccess())
            redirectAttributes.addFlashAttribute("result", new ResultResponse("Se ingreso correctamente el registro al catalogo", "alert alert-success"));
        else
            redirectAttributes.addFlashAttribute("result", new ResultResponse(response.getMessage(), "alert alert-danger"));
        return "redirect:/formulario/programacion/index";
    }

    private ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>> setCookieFilter(ParametrosPaginacionBusquedaRequest<FilterParamsRequest> model, FilterParamsRequest filter, boolean cookie, String value) throws JsonProcessingException {
        var requestFilter = createCoockie(cookie, value, filter);
        var parametros = new ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>>();
        parametros.setBuscar(model.getBuscar());
        parametros.setMostrar(model.getMostrar());
        parametros.setPagina(model.getPagina());
        parametros.setOption(requestFilter);
        return parametros;
    }

    private FilterRequest<FormularioFilterRequest> createCoockie(boolean cookie, String value, FilterParamsRequest filter) throws JsonProcessingException {
        if (cookieFilter == null) cookieFilter = new CookieFilter<>("filter", request, response);
        if (cookie) {
            cookieFilter.reset();
            value = null;
        }
        FilterRequest<FormularioFilterRequest> requestFilter;
        if (value == null) {
            cookieFilter.init();
            requestFilter = new FilterRequest<>();
        } else
            requestFilter = objectMapper.readValue(value, new TypeReference<FilterRequest<FormularioFilterRequest>>() {
            });
        if (requestFilter.getFilter() == null) requestFilter.setFilter(new FormularioFilterRequest());
        ReflectionUtils.toAssinateValue(requestFilter.getFilter(), filter.getOption(), filter.getValue());
        requestFilter.setParams(FiltersUtils.setValue(requestFilter.getParams(), filter));
        cookieFilter.setData(requestFilter);
        return requestFilter;
    }
}
