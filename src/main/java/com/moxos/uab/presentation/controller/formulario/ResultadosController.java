package com.moxos.uab.presentation.controller.formulario;

import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.dto.request.accionestrategica.AccionEstrategicaRequest;
import com.moxos.uab.domain.dto.request.resultados.ResultadosRequest;
import com.moxos.uab.domain.dto.request.resultadosgestion.ResultadosGestionRequest;
import com.moxos.uab.domain.entity.siiga.Clientes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class Resultados {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;
    private final IFormularioProgramacionFacade formularioProgramacionFacade;

    public Resultados(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade, IFormularioProgramacionFacade formularioProgramacionFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
        this.formularioProgramacionFacade = formularioProgramacionFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/acciones-estrategicas/new")
    public String nuevo(@ModelAttribute("model") ResultadosRequest model, Model modelo) {
        modelo.addAttribute("model", model);
        modelo.addAttribute("objetivo", politicasIndicadoresAreasFacade.getAccionesEstrategicasModel(model.getId_acciones_estrategica()));
        modelo.addAttribute("unidad", politicasIndicadoresAreasFacade.getUnidadMedidaModel(model.getId_unidad_medida()));
        modelo.addAttribute("tipo", politicasIndicadoresAreasFacade.getTipoIndicadorModel(model.getId_unidad_medida()));
        modelo.addAttribute("categoria", politicasIndicadoresAreasFacade.getCategoriaIndicadorModel(model.getId_categoria()));
        modelo.addAttribute("formulario", formularioProgramacionFacade.getFormularioProgramacionModel(model.getId_formulario()));
        return "AccionesEstrategicas/_New";
    }
}
