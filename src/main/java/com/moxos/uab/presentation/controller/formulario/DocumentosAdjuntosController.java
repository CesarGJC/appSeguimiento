package com.moxos.uab.presentation.controller.formulario;

import com.moxos.uab.business.facade.IAdjuntarDocumentosFacade;
import com.moxos.uab.domain.dto.request.adjuntos.AdjuntosRequest;
import com.moxos.uab.domain.dto.response.adjuntos.DocumentosTiposAdjuntosResponse;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
@RequestMapping("/documento/adjunto")
public class DocumentosAdjuntosController {
    private final IAdjuntarDocumentosFacade adjuntarDocumentosFacade;

    public DocumentosAdjuntosController(IAdjuntarDocumentosFacade adjuntarDocumentosFacade) {
        this.adjuntarDocumentosFacade = adjuntarDocumentosFacade;
    }

    @GetMapping("/index")
    public String index(@ModelAttribute("id") Integer id, Model modelo) {
        DocumentosTiposAdjuntosResponse response = adjuntarDocumentosFacade.getDocumentosPorActividad(id);
        modelo.addAttribute("documentos", response);
        return "Formulario/Adjunto/Index";
    }

    @GetMapping("/upload")
    public String upload(@ModelAttribute("id") Integer id, Model modelo) {
        var response = adjuntarDocumentosFacade.getDocumentoRequest(id);
        modelo.addAttribute("model", response);
        return "Formulario/Adjunto/_Upload";
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @PostMapping("/upload")
    public String upload(@ModelAttribute("model") @Valid AdjuntosRequest model, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("model", model);
            return "Formulario/Adjunto/_Upload";
        }
        model.setUlt_usuario(getUsuario().getId_usuario());
        var response = adjuntarDocumentosFacade.saveDocumentoAdjunto(model);
        if (!response.isSuccess()) {
            result.addError(new FieldError("model", "file", response.getMessage()));
            modelo.addAttribute("model", model);
            return "Formulario/Adjunto/_Upload";
        }
        modelo.addAttribute("item", response.getResult());
        if (model.getTipo_documento().equals("image"))
            return "Formulario/Adjunto/_Update";
        else return "Formulario/Adjunto/_UpdateDocumento";
    }

    @GetMapping("/download")
    public void downloadFile(@ModelAttribute("id") Integer id, HttpServletResponse response) throws IOException {
        adjuntarDocumentosFacade.downloadDocumentoAdjunto(id, response);
    }
    @GetMapping("/download-documento")
    public void downloadFileDocumento(@ModelAttribute("id") Integer id, HttpServletResponse response) throws IOException {
        adjuntarDocumentosFacade.downloadDocumentoAdjuntoDocumento(id, response);
    }
}
