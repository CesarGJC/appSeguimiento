package com.moxos.uab.presentation.controller.formulario;

import com.moxos.uab.business.facade.IFormularioProgramacionFacade;
import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import com.moxos.uab.domain.entity.siiga.Clientes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/programacion/detalle")
public class ProgramacionController {
    private final IFormularioProgramacionFacade formularioProgramacionFacade;
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;

    public ProgramacionController(IFormularioProgramacionFacade formularioProgramacionFacade, IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.formularioProgramacionFacade = formularioProgramacionFacade;
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/index/{id}")
    public String index(@PathVariable Integer id, Model model) {
        var formularioProgramacion = formularioProgramacionFacade.getFormularioProgramacionDetalle(id);
        model.addAttribute("formulario", formularioProgramacion);
        return "Formulario/Detalle/Index";
    }
}
