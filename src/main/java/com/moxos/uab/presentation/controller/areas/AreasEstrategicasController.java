package com.moxos.uab.presentation.controller.areas;

import com.moxos.uab.business.facade.IPoliticasIndicadoresAreasFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AreasEstrategicasController {
    private final IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade;


    public AreasEstrategicasController(IPoliticasIndicadoresAreasFacade politicasIndicadoresAreasFacade) {
        this.politicasIndicadoresAreasFacade = politicasIndicadoresAreasFacade;
    }
    @GetMapping("/areas-estrategicas/index")
    public String index(Model model) {
        return "AreasEstrategicas/Index";
    }
}
