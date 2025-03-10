package com.moxos.uab.presentation.controller.formulario;

import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.accionestrategica.AccionEstrategicaRequest;
import com.moxos.uab.domain.dto.request.objetivosestrategicos.ObjetivosEstrategicosRequest;
import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.resultadosgestion.ResultadosGestionDetalleResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resultados")
public class ResultadosController {
    private final IFormularioProgramacionFacade formularioProgramacionFacade;
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public ResultadosController(IFormularioProgramacionFacade formularioProgramacionFacade, IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.formularioProgramacionFacade = formularioProgramacionFacade;
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/new")
    public String nuevo(@ModelAttribute("model") ResultadosRequest model, Model modelo) {
        model.setCatalogosIndicadores(formularioProgramacionFacade.getCatalogosIndicadores(model.getId_area_estrategica()));
        modelo.addAttribute("model", model);
        modelo.addAttribute("acciones", politicasIndicadoresAreasFacade.getAccionesEstrategicasDetalle(model.getId_acciones_estrategica()));
        modelo.addAttribute("periodos", formularioProgramacionFacade.getDetallePeriodoProgramacion(model));
        return "Formulario/Resultados/New";
    }

    @PostMapping("/new")
    public String nuevo(@ModelAttribute("model") @Valid ResultadosRequest model, BindingResult result, Model modelo, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.setCatalogosIndicadores(formularioProgramacionFacade.getCatalogosIndicadores(model.getId_area_estrategica()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("acciones", politicasIndicadoresAreasFacade.getAccionesEstrategicasDetalle(model.getId_acciones_estrategica()));
            modelo.addAttribute("periodos", formularioProgramacionFacade.getDetallePeriodoProgramacion(model));
            return "Formulario/Resultados/New";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        var response = formularioProgramacionFacade.saveResultados(model, httpServletRequest);
        if (!response.isSuccess()) {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            model.setCatalogosIndicadores(formularioProgramacionFacade.getCatalogosIndicadores(model.getId_area_estrategica()));

            modelo.addAttribute("model", model);
            modelo.addAttribute("acciones", politicasIndicadoresAreasFacade.getAccionesEstrategicasDetalle(model.getId_acciones_estrategica()));
            modelo.addAttribute("periodos", formularioProgramacionFacade.getDetallePeriodoProgramacion(model));
            return "Formulario/Resultados/New";
        }
        return "redirect:/programacion/detalle/index/" + model.getId_formulario();
    }

    @GetMapping("/update")
    public String update(@ModelAttribute("id") Integer id, @ModelAttribute("id_plan_pei") Integer id_plan_pei, Model modelo) {
        ResultadosRequest model = formularioProgramacionFacade.getResultadoByid(id).getResult();
        model.setId_plan_pei(id_plan_pei);
        model.setCatalogosIndicadores(formularioProgramacionFacade.getCatalogosIndicadores(model.getId_area_estrategica()));
        modelo.addAttribute("model", model);
        modelo.addAttribute("acciones", politicasIndicadoresAreasFacade.getAccionesEstrategicasDetalle(model.getId_acciones_estrategica()));
        modelo.addAttribute("periodos", formularioProgramacionFacade.getDetallePeriodoProgramacion(model));
        return "Formulario/Resultados/Update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("model") @Valid ResultadosRequest model, BindingResult result, Model modelo, HttpServletRequest httpServletRequest) {
        if (result.hasErrors()) {
            model.setCatalogosIndicadores(formularioProgramacionFacade.getCatalogosIndicadores(model.getId_area_estrategica()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("acciones", politicasIndicadoresAreasFacade.getAccionesEstrategicasDetalle(model.getId_acciones_estrategica()));
            modelo.addAttribute("periodos", formularioProgramacionFacade.getDetallePeriodoProgramacion(model));
            return "Formulario/Resultados/Update";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        var response = formularioProgramacionFacade.updateResultados(model, httpServletRequest);
        if (!response.isSuccess()) {
            result.addError(new FieldError("model", "descripcion", response.getMessage()));
            model.setCatalogosIndicadores(formularioProgramacionFacade.getCatalogosIndicadores(model.getId_area_estrategica()));
            modelo.addAttribute("model", model);
            modelo.addAttribute("acciones", politicasIndicadoresAreasFacade.getAccionesEstrategicasDetalle(model.getId_acciones_estrategica()));
            modelo.addAttribute("periodos", formularioProgramacionFacade.getDetallePeriodoProgramacion(model));
            return "Formulario/Resultados/Update";
        }
        return "redirect:/programacion/detalle/index/" + model.getId_formulario();
    }

    @GetMapping("/delete")
    public String eliminar(@ModelAttribute("id") Integer id, Model modelo) {
        ResultadosRequest model = formularioProgramacionFacade.getResultadoByid(id).getResult();
        modelo.addAttribute("model", model);
        return "Formulario/Resultados/_Delete";
    }

    @PostMapping("/delete")
    public String eliminar(@ModelAttribute("model") ResultadosRequest model, Model modelo, RedirectAttributes redirectAttributes) {
        GeneralResponse response = formularioProgramacionFacade.deleteResultados(model);
        if (!response.isSuccess()) {
            modelo.addAttribute("status", false);
            modelo.addAttribute("message", response.getMessage());
            return "Formulario/Resultados/_Notificacion";
        }
        redirectAttributes.addAttribute("id", model.getId_formulario());
        return "redirect:/programacion/detalle/formulario";
    }

}
