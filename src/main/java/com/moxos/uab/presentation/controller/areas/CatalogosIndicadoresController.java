package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.catalogoindicadores.CatalogoIndicadoresRequest;
import com.moxos.uab.domain.dto.request.catalogoindicadores.ParametroAreaEstrategicaRequest;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.catalogoindicadores.CatalogoIndicadoresResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogosIndicadoresController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public CatalogosIndicadoresController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/catalogo-indicadores/index/{id}")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<ParametroAreaEstrategicaRequest> model, @PathVariable Integer id, Model modelo) {
        model.setOption(new ParametroAreaEstrategicaRequest(id, 0));
        var areaEstrategicasDetalle = politicasIndicadoresAreasFacade.getAreaEstrategicasModel(id);
        //Lista de opciones de busqueda
        List<SelectListItemDto> opcionesElementos = new ArrayList<>();
        opcionesElementos.add(new SelectListItemDto("0", "DENOMINACION DE INDICADORES"));
        opcionesElementos.add(new SelectListItemDto("1", "ARTICULACION"));

        modelo.addAttribute("opciones", opcionesElementos);
        modelo.addAttribute("model", model);
        modelo.addAttribute("areaEstrategica", areaEstrategicasDetalle);
        return "CatalogosIndicadores/Index";
    }

    @GetMapping("/catalogo-indicadores/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<ParametroAreaEstrategicaRequest> model, @ModelAttribute("parametros") ParametroAreaEstrategicaRequest parametros, Model modelo) {
        model.setOption(parametros);
        var paginacion = politicasIndicadoresAreasFacade.getCatalogosIndicadores(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());
        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "CatalogosIndicadores/_Listar";
    }

    @GetMapping("/catalogo-indicadores/update")
    public String editar(@ModelAttribute("model") CatalogoIndicadoresRequest model, Model modelo) {
        CatalogoIndicadoresRequest catalogoIndicadoresRequest = politicasIndicadoresAreasFacade.getCatalogoIndicadoresModel(model.getId_catalogo_indicador_pei());
        catalogoIndicadoresRequest.setCategorias(politicasIndicadoresAreasFacade.getCategoriaIndicador());
        catalogoIndicadoresRequest.setTiposIndicadores(politicasIndicadoresAreasFacade.getTiposIndicadores());
        catalogoIndicadoresRequest.setTiposUnidades(politicasIndicadoresAreasFacade.getUnidadesMedidas());
        modelo.addAttribute("model", catalogoIndicadoresRequest);
        return "CatalogosIndicadores/_Update";
    }

    @PostMapping("/catalogo-indicadores/update")
    public String editar(@ModelAttribute("model") @Valid CatalogoIndicadoresRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            model.setCategorias(politicasIndicadoresAreasFacade.getCategoriaIndicador());
            model.setTiposIndicadores(politicasIndicadoresAreasFacade.getTiposIndicadores());
            model.setTiposUnidades(politicasIndicadoresAreasFacade.getUnidadesMedidas());
            modelo.addAttribute("model", model);
            return "CatalogosIndicadores/_Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<CatalogoIndicadoresResponse> response = politicasIndicadoresAreasFacade.saveCatalogoIndicadores(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "CatalogosIndicadores/_Filas";
        } else {
            result.addError(new FieldError("model", "catalogo-indicadores", response.getMessage()));
            modelo.addAttribute("model", model);
            return "CatalogosIndicadores/_Update";
        }
    }

    @GetMapping("/catalogo-indicadores/new")
    public String nuevo(@ModelAttribute("model") CatalogoIndicadoresRequest model, Model modelo) {
        model.setId_categoria(-1);
        model.setCategorias(politicasIndicadoresAreasFacade.getCategoriaIndicador());
        model.setId_tipo_indicador(-1);
        model.setTiposIndicadores(politicasIndicadoresAreasFacade.getTiposIndicadores());
        model.setId_unidad_medida(-1);
        model.setTiposUnidades(politicasIndicadoresAreasFacade.getUnidadesMedidas());
        var areaEstrategicasDetalle = politicasIndicadoresAreasFacade.getAreaEstrategicasModel(model.getId_area_estrategica());
        modelo.addAttribute("model", model);
        modelo.addAttribute("areaEstrategicasDetalle", areaEstrategicasDetalle);
        return "CatalogosIndicadores/New";
    }

    @PostMapping("/catalogo-indicadores/new")
    public String nuevo(@ModelAttribute("model") @Valid CatalogoIndicadoresRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            model.setCategorias(politicasIndicadoresAreasFacade.getCategoriaIndicador());
            model.setTiposIndicadores(politicasIndicadoresAreasFacade.getTiposIndicadores());
            model.setTiposUnidades(politicasIndicadoresAreasFacade.getUnidadesMedidas());
            modelo.addAttribute("model", model);
            return "CatalogosIndicadores/_New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        Response<CatalogoIndicadoresResponse> response = politicasIndicadoresAreasFacade.saveCatalogoIndicadores(model);
        if (response.isSuccess()) {
            modelo.addAttribute("item", response.getResult());
            return "CatalogosIndicadores/_Filas";
        } else {
            result.addError(new FieldError("model", "indicadores_estrategicos", response.getMessage()));
            modelo.addAttribute("model", model);
            return "CatalogosIndicadores/_New";
        }
    }

    @GetMapping("/catalogo-indicadores/delete")
    public String eliminar(@ModelAttribute("model") CatalogoIndicadoresRequest model, Model modelo) {
        CatalogoIndicadoresRequest catalogoIndicadoresRequest = politicasIndicadoresAreasFacade.getCatalogoIndicadoresModel(model.getId_catalogo_indicador_pei());
        modelo.addAttribute("model", catalogoIndicadoresRequest);
        return "CatalogosIndicadores/_Delete";
    }

    @PostMapping("/catalogo-indicadores/delete")
    public String eliminarCatalogo(@ModelAttribute("model") @Valid CatalogoIndicadoresRequest model, Model modelo) {
        GeneralResponse response = politicasIndicadoresAreasFacade.deleteCatalogoIndicadores(model);
        if (response.isSuccess()) {
            modelo.addAttribute("status", true);
            modelo.addAttribute("message", "Se elimino correctamente");
        } else {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
        }
        return "CatalogosIndicadores/_Notificacion";
    }

    @GetMapping("/catalogo-indicadores/listar-filtrar")
    public String filtrar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<ParametroAreaEstrategicaRequest> model, Model modelo) {
        var paginacion = politicasIndicadoresAreasFacade.getCatalogosIndicadores(model);
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());
        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "CatalogosIndicadores/_Listar";
    }

    @GetMapping("/catalogo-indicadores/indicadores")
    public String politicas(@ModelAttribute("id") Integer id, Model modelo) {
        var listaIndicadoresEstrategicos = politicasIndicadoresAreasFacade.getIndicadoresEstrategicos(id);
        modelo.addAttribute("indicadores", listaIndicadoresEstrategicos);
        return "CatalogosIndicadores/_option";
    }
}
