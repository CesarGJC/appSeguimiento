package com.moxos.uab.presentation.controller.formulario;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moxos.uab.business.facade.IActividadesProgramadasFacade;
import com.moxos.uab.business.facade.IConfiguracionFacade;
import com.moxos.uab.common.util.ReflectionUtils;
import com.moxos.uab.common.util.filter.FiltersUtils;
import com.moxos.uab.common.util.web.CookieFilter;
import com.moxos.uab.domain.dto.request.general.*;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesFilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.request.operaciones.ProgramasActividadesUnidadRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.resultados.ResultadosDetalleResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/operaciones")
public class OperacionesController {
    private CookieFilter<FilterRequest<OperacionesFilterRequest>> cookieFilter;
    private final IActividadesProgramadasFacade actividadesFacade;
    private final IConfiguracionFacade configuracionFacade;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OperacionesController(IActividadesProgramadasFacade actividadesFacade, IConfiguracionFacade configuracionFacade, HttpServletRequest request, HttpServletResponse response) {
        this.actividadesFacade = actividadesFacade;
        this.configuracionFacade = configuracionFacade;
        this.request = request;
        this.response = response;
    }


    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/index")
    public String index(RedirectAttributes redirectAttributes) {
        if (getUsuario().getId_programa() != 0 & getUsuario().getId_departamento() != 0) {
            return "redirect:/operaciones/seleccionar-unidad";
        }
        if (getUsuario().getId_programa() == 0 & getUsuario().getId_departamento() != 0) {
            redirectAttributes.addAttribute("id_departamento", getUsuario().getId_departamento());
            return "redirect:/operaciones/listar-planes";
        }
        if (getUsuario().getId_programa() != 0 && getUsuario().getId_departamento() == 0) {
            redirectAttributes.addAttribute("id_programa", getUsuario().getId_programa());
            return "redirect:/operaciones/listar-planes";
        }
        if ((getUsuario().getId_programa() == 0 || getUsuario().getId_departamento() == 0) && getUsuario().getId_facultad() != 0) {
            return "redirect:/operaciones/seleccionar-programa";
        }
        return "Formulario/Operaciones/AccesoDenegado";
    }

    @GetMapping("/listar-planes")
    public String listarPlanes(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, Model modelo) {
        int idPlanPei = Integer.parseInt(configuracionFacade.getValorConfiguracionPorClave("id_plan_pei"));
        List<FormularioResponse> formularioResponsesList = configuracionFacade.getFormulariosPorPlan(model.getId_programa() == 0 ? model.getId_departamento() : model.getId_programa(), model.getId_programa() == 0 ? 1 : 2, idPlanPei);

        modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(model.getId_departamento(), model.getId_programa()));
        modelo.addAttribute("formularioResponsesList", formularioResponsesList);
        modelo.addAttribute("model", model);
        return "Formulario/Operaciones/Index";
    }

    @GetMapping("/seleccionar-unidad")
    public String seleccionarUnidadAcademicoAdministrativa(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, Model modelo) {
        List<ListView> tiposUnidades = new ArrayList<>();
        tiposUnidades.add(new ListView("1", "Unidades administrativas"));
        tiposUnidades.add(new ListView("2", "Unidades academicas"));
        modelo.addAttribute("tiposUnidades", tiposUnidades);
        modelo.addAttribute("model", model);
        return "Formulario/Operaciones/SeleccionarAcademicaAdministrativa";
    }

    @PostMapping("/seleccionar-unidad")
    public String seleccionarUnidadAcademicoAdministrativa(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", model.getId());
        if (model.getTipoUnidad() == 1) {
            redirectAttributes.addAttribute("id_departamento", getUsuario().getId_departamento());
            return "redirect:/operaciones/listar-planes";
        }
        redirectAttributes.addAttribute("id_programa", getUsuario().getId_programa());
        return "redirect:/operaciones/listar-planes";
    }

    @GetMapping("/seleccionar-tipo")
    public String seleccionarUnidad(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, Model modelo) {
        List<ListView> tiposUnidades = new ArrayList<>();
        tiposUnidades.add(new ListView("1", "Unidades administrativas"));
        tiposUnidades.add(new ListView("2", "Unidades academicas"));
        modelo.addAttribute("tiposUnidades", tiposUnidades);
        modelo.addAttribute("model", model);
        return "Formulario/Operaciones/SeleccionarTipoUnidad";
    }

    @PostMapping("/seleccionar-tipo")
    public String seleccionarUnidad(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, RedirectAttributes redirectAttributes) {
        if (model.getTipoUnidad() == 1) {
            model.setId_departamento(getUsuario().getId_departamento());
            redirectAttributes.addAttribute("id_departamento", getUsuario().getId_departamento());
            redirectAttributes.addAttribute("id", model.getId());
            return "redirect:/operaciones/actividades";
        }
        if (model.getTipoUnidad() == 2) {
            if (getUsuario().getId_programa() != 0) {
                model.setId_programa(getUsuario().getId_departamento());
                redirectAttributes.addAttribute("id_programa", getUsuario().getId_programa());
                redirectAttributes.addAttribute("id", model.getId());
                return "redirect:/operaciones/actividades";
            }
            if (getUsuario().getId_programa() == 0 && getUsuario().getId_facultad() != 0) {
                redirectAttributes.addAttribute("id", model.getId());
                return "redirect:/operaciones/seleccionar-programa";
            }
        }
        return "redirect:/operaciones/actividades";
    }

    @GetMapping("/seleccionar-programa")
    public String seleccionarPrograma(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, Model modelo) {
        List<ListView> programasUnidades = actividadesFacade.getListaPrograma(getUsuario().getId_facultad());
        modelo.addAttribute("programasUnidades", programasUnidades);
        modelo.addAttribute("model", model);
        return "Formulario/Operaciones/SeleccionarPrograma";
    }

    @PostMapping("/seleccionar-programa")
    public String seleccionarPrograma(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", model.getId());
        redirectAttributes.addAttribute("id_programa", model.getId_programa());
        return "redirect:/operaciones/listar-planes";
    }

    @GetMapping("/actividades")
    public String actividades(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<OperacionesFilterRequest> model, @ModelAttribute("modelPrograma") ProgramasActividadesUnidadRequest modelPrograma, Model modelo) {
        model.setOption(new OperacionesFilterRequest());
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemTypeDto("operaciones", "ACTIVIDAD", 0));
        opcionesElementos.add(new SelectListItemTypeDto("gestion", "GESTION", 0));
        opcionesElementos.add(new SelectListItemTypeDto("elaborador", "ELABORADO POR", 0));
        opcionesElementos.add(new SelectListItemTypeDto("resultado", "RESULTADO", 0));
        opcionesElementos.add(new SelectListItemTypeDto("indicador", "INDICADOR", 0));
        opcionesElementos.add(new SelectListItemTypeDto("titulo", "TITULO", 0));
        var response = actividadesFacade.getFormularioPorId(modelPrograma.getId());

        modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(modelPrograma.getId_departamento(), modelPrograma.getId_programa()));
        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        modelo.addAttribute("modelPrograma", modelPrograma);
        modelo.addAttribute("item", response);
        return "Formulario/Operaciones/Actividades";
    }

    @GetMapping("/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<FilterParamsRequest> model, @ModelAttribute("filter") FilterParamsRequest filter, @ModelAttribute("modelPrograma") ProgramasActividadesUnidadRequest modelPrograma, @ModelAttribute("cookie") boolean cookie, @CookieValue(value = "filter", required = false) String value, Model modelo) throws JsonProcessingException {
        var paginacion = actividadesFacade.getActividadesProgramacion(setCookieFilter(model, filter, cookie, value), modelPrograma);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());
        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "Formulario/Operaciones/_Listar";
    }

    @GetMapping("/new")
    public String nuevo(@ModelAttribute("model") OperacionesRequest model, Model modelo) {
        model.setId_detalle_periodos_programacion(Integer.parseInt(configuracionFacade.getValorConfiguracionPorClave("id_periodo_gestion")));
        OperacionesRequest response = actividadesFacade.crearOperacionesRequest(model);
        modelo.addAttribute("model", response);
        modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(model.getId_departamento(), model.getId_programa()));
        return "Formulario/Operaciones/New";
    }

    @PostMapping("/new")
    public String nuevo(@ModelAttribute("model") @Valid OperacionesRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(model.getId_departamento(), model.getId_programa()));
            modelo.addAttribute("resultadoEsperado", actividadesFacade.getResultadoPorPeriodoGestionPorId(model.getId_resultados(), model.getId_detalle_periodos_programacion()));
            modelo.addAttribute("model", actividadesFacade.crearOperacionesRequest(model));
            return "Formulario/Operaciones/New";
        }
        return redirigirActividades(model, result, modelo, redirectAttributes, "Formulario/Operaciones/New");
    }

    @GetMapping("/update")
    public String update(@ModelAttribute("model") OperacionesRequest model, Model modelo) {
        model = actividadesFacade.getOperacionesRequest(model);
        OperacionesRequest response = actividadesFacade.crearOperacionesRequest(model);
        modelo.addAttribute("model", response);
        modelo.addAttribute("resultadoEsperado", actividadesFacade.getResultadoPorPeriodoGestionPorId(response.getId_resultados(), response.getId_detalle_periodos_programacion()));
        modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(model.getId_departamento(), model.getId_programa()));
        return "Formulario/Operaciones/Update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("model") @Valid OperacionesRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", actividadesFacade.crearOperacionesRequest(model));
            modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(model.getId_departamento(), model.getId_programa()));
            modelo.addAttribute("resultadoEsperado", actividadesFacade.getResultadoPorPeriodoGestionPorId(model.getId_resultados(), model.getId_detalle_periodos_programacion()));
            return "Formulario/Operaciones/Update";
        }
        return redirigirActividades(model, result, modelo, redirectAttributes, "Formulario/Operaciones/Update");
    }

    @GetMapping("/delete")
    public String eliminar(@ModelAttribute("model") OperacionesRequest model, Model modelo) {
        OperacionesRequest operacionesRequest = actividadesFacade.getOperacionesRequest(model);
        modelo.addAttribute("model", operacionesRequest);
        return "Formulario/Operaciones/_Delete";
    }

    @PostMapping("/delete")
    public String eliminarFormulario(@ModelAttribute("model") OperacionesRequest model, Model modelo) {
        GeneralResponse response = actividadesFacade.deleteOperaciones(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "Formulario/Operaciones/_Notificacion";
    }

    @GetMapping("/resultado")
    public String resultado(@ModelAttribute("id") Integer id, @ModelAttribute("idperiodogestion") Integer idPeriodoGestion, Model modelo) {
        ResultadosDetalleResponse response = actividadesFacade.getResultadoPorPeriodoGestionPorId(id, idPeriodoGestion);
        modelo.addAttribute("resultadoEsperado", response);
        return "Formulario/Operaciones/_Resultado";
    }

    private String redirigirActividades(OperacionesRequest model, BindingResult result, Model modelo, RedirectAttributes redirectAttributes, String ruta) {
        model.setUlt_usuario(getUsuario().getId_usuario());
        var response = actividadesFacade.saveOperaciones(model);
        if (!response.isSuccess()) {
            result.addError(new FieldError("model", "titulo", response.getMessage()));
            modelo.addAttribute("model", actividadesFacade.crearOperacionesRequest(model));
            modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(getUsuario().getId_facultad(), getUsuario().getId_programa()));
            modelo.addAttribute("resultadoEsperado", actividadesFacade.getResultadoPorPeriodoGestionPorId(model.getId_resultados(), model.getId_detalle_periodos_programacion()));
            return ruta;
        }
        redirectAttributes.addAttribute("id", model.getId_formulario());
        redirectAttributes.addAttribute("id_programa", model.getId_programa());
        redirectAttributes.addAttribute("id_departamento", model.getId_departamento());
        return "redirect:/operaciones/actividades";
    }

    private ParametrosPaginacionBusquedaRequest<FilterRequest<OperacionesFilterRequest>> setCookieFilter(ParametrosPaginacionBusquedaRequest<FilterParamsRequest> model, FilterParamsRequest filter, boolean cookie, String value) throws JsonProcessingException {
        var requestFilter = createCoockie(cookie, value, filter);
        var parametros = new ParametrosPaginacionBusquedaRequest<FilterRequest<OperacionesFilterRequest>>();
        parametros.setBuscar(model.getBuscar());
        parametros.setMostrar(model.getMostrar());
        parametros.setPagina(model.getPagina());
        parametros.setOption(requestFilter);
        return parametros;
    }

    private FilterRequest<OperacionesFilterRequest> createCoockie(boolean cookie, String value, FilterParamsRequest filter) throws JsonProcessingException {
        if (cookieFilter == null) cookieFilter = new CookieFilter<>("filterActividades", request, response);
        if (cookie) {
            cookieFilter.reset();
            value = null;
        }
        FilterRequest<OperacionesFilterRequest> requestFilter;
        if (value == null) {
            cookieFilter.init();
            requestFilter = new FilterRequest<>();
        } else
            requestFilter = objectMapper.readValue(value, new TypeReference<>() {
            });
        if (requestFilter.getFilter() == null) requestFilter.setFilter(new OperacionesFilterRequest());
        ReflectionUtils.toAssinateValue(requestFilter.getFilter(), filter.getOption(), filter.getValue());
        requestFilter.setParams(FiltersUtils.setValue(requestFilter.getParams(), filter));
        cookieFilter.setData(requestFilter);
        return requestFilter;
    }
}