package com.moxos.uab.presentation.controller.formulario.poa;

import com.moxos.uab.business.facade.IActividadesProgramadasFacade;
import com.moxos.uab.business.facade.IConfiguracionFacade;
import com.moxos.uab.business.facade.IFormularioPoaFacade;
import com.moxos.uab.domain.dto.request.operaciones.ProgramasActividadesUnidadRequest;
import com.moxos.uab.domain.dto.response.formulario.FormularioResponse;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.siiga.Clientes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/poa")
public class ProgramacionPoaController {
    private final IFormularioPoaFacade formularioPoaFacade;
    private final IConfiguracionFacade configuracionFacade;
    private final IActividadesProgramadasFacade actividadesFacade;

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/index")
    public String index(RedirectAttributes redirectAttributes) {
        if (getUsuario().getId_programa() != 0 & getUsuario().getId_departamento() != 0) {
            return "redirect:/poa/seleccionar-unidad";
        }
        if (getUsuario().getId_programa() == 0 & getUsuario().getId_departamento() != 0) {
            redirectAttributes.addAttribute("id_departamento", getUsuario().getId_departamento());
            return "redirect:/poa/listar-planes";
        }
        if (getUsuario().getId_programa() != 0 && getUsuario().getId_departamento() == 0) {
            redirectAttributes.addAttribute("id_programa", getUsuario().getId_programa());
            return "redirect:/poa/listar-planes";
        }
        if ((getUsuario().getId_programa() == 0 || getUsuario().getId_departamento() == 0) && getUsuario().getId_facultad() != 0) {
            return "redirect:/poa/seleccionar-programa";
        }
        return "FormularioPoa/Operaciones/AccesoDenegado";
    }

    @GetMapping("/seleccionar-unidad")
    public String seleccionarUnidadAcademicoAdministrativa(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, Model modelo) {
        List<ListView> tiposUnidades = new ArrayList<>();
        tiposUnidades.add(new ListView("1", "Unidades administrativas"));
        tiposUnidades.add(new ListView("2", "Unidades academicas"));
        modelo.addAttribute("tiposUnidades", tiposUnidades);
        modelo.addAttribute("model", model);
        return "FormularioPoa/Operaciones/SeleccionarAcademicaAdministrativa";
    }

    @PostMapping("/seleccionar-unidad")
    public String seleccionarUnidadAcademicoAdministrativa(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", model.getId());
        if (model.getTipoUnidad() == 1) {
            redirectAttributes.addAttribute("id_departamento", getUsuario().getId_departamento());
            return "redirect:/poa/listar-planes";
        }
        redirectAttributes.addAttribute("id_programa", getUsuario().getId_programa());
        return "redirect:/poa/listar-planes";
    }

    @GetMapping("/seleccionar-programa")
    public String seleccionarPrograma(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, Model modelo) {
        List<ListView> programasUnidades = actividadesFacade.getListaPrograma(getUsuario().getId_facultad());
        modelo.addAttribute("programasUnidades", programasUnidades);
        modelo.addAttribute("model", model);
        return "FormularioPoa/Operaciones/SeleccionarPrograma";
    }

    @GetMapping("/listar-planes")
    public String listarPlanes(@ModelAttribute("model") ProgramasActividadesUnidadRequest model, Model modelo) {
        int idPlanPei = Integer.parseInt(configuracionFacade.getValorConfiguracionPorClave("id_plan_pei"));
        List<FormularioResponse> formularioResponsesList = configuracionFacade.getFormulariosPorPlan(model.getId_programa() == 0 ? model.getId_departamento() : model.getId_programa(), model.getId_programa() == 0 ? 1 : 2, idPlanPei);

        modelo.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(model.getId_departamento(), model.getId_programa()));
        modelo.addAttribute("formularioResponsesList", formularioResponsesList);
        modelo.addAttribute("model", model);
        return "FormularioPoa/Operaciones/Index";
    }

    @GetMapping("/new/{id}")
    public String index(@PathVariable Integer id, @RequestParam("id_programa") Integer idPrograma, @RequestParam("id_departamento") Integer idDepartamento, Model model) {
        var formularioProgramacionPoa = formularioPoaFacade.getFormularioProgramacionPoaCabecera(id);
        model.addAttribute("formulario", formularioProgramacionPoa);
        model.addAttribute("id_programa", idPrograma);
        model.addAttribute("id_departamento", idDepartamento);
        model.addAttribute("programaResponse", actividadesFacade.getProgramaFacultad(idDepartamento, idPrograma));
        return "FormularioPoa/Programacion/Index";
    }

    @GetMapping("/formulario")
    public String formulario(@ModelAttribute("id") Integer id, Model model, @RequestParam("id_programa") Integer idPrograma, @RequestParam("id_departamento") Integer idDepartamento, @RequestParam("id_detalle_periodos_programacion") Integer idDetallePeriodoGestion) {
        var formularioProgramacionpoa = formularioPoaFacade.getFormularioProgramacionPoaDetalle(id, idPrograma, idDepartamento, idDetallePeriodoGestion);
        model.addAttribute("formulario", formularioProgramacionpoa);
        return "FormularioPoa/Detalle/_Listar";
    }
}
