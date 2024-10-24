package com.moxos.uab.presentation.controller.index;

import com.moxos.uab.business.facade.IAuthenticationFacade;
import com.moxos.uab.domain.entity.siiga.Accesos;
import com.moxos.uab.domain.entity.siiga.Clientes;
import com.moxos.uab.domain.entity.siiga.Roles;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class IndexController {

    private final IAuthenticationFacade authenticationFacade;
    private final HttpServletRequest request;

    public IndexController(HttpServletRequest request, IAuthenticationFacade authenticationFacade) {
        this.request = request;
        this.authenticationFacade = authenticationFacade;
    }

    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/index")
    public String index(Model modelo) {
        modelo.addAttribute("fil", authenticationFacade.getMenuSistema(getUsuario()));
        modelo.addAttribute("simagen", getUsuario().getImagen());
        modelo.addAttribute("cliente", getUsuario());
        modelo.addAttribute("snombre", getUsuario().getNombres().substring(0, 10) + "..");
        return "Menu/Menu";
    }
    @RequestMapping("/main")
    public String main(Model modelo) {
        modelo.addAttribute("snombre", this.getUsuario().getNombres());
        return "Menu/Main";
    }
    @RequestMapping("/elegir-rol")
    public String elegirRol(Model modelo, Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        modelo.addAttribute("cliente", this.getUsuario());
        return "Menu/ElegirRol";
    }

    @PostMapping("/cambiar-rol")
    public String cambiarRol(@ModelAttribute("id_rol") Integer id_rol) {
        Clientes cliente = authenticationFacade.getBuscarRolCliente(id_rol, this.getUsuario());
        Accesos acceso = authenticationFacade.asignarAccesos(cliente);
        request.getSession().setAttribute("__sess_acceso", acceso);
        request.getSession().setAttribute("__sess_cliente", cliente);
        return "redirect:/index";
    }

}
