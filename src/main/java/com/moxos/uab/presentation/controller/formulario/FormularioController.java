package com.moxos.uab.presentation.controller.formulario;

import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FormularioController {
    @GetMapping("/formulario/index")
    public String index() {
        return "Formulario/Index";
    }

}
