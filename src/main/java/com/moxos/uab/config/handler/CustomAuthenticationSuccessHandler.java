package com.moxos.uab.config.handler;


import com.moxos.uab.business.facade.IAuthenticationFacade;
import com.moxos.uab.domain.entity.siiga.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final IAuthenticationFacade mi;
    @Value("${app.upload.path}")
    private String path;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public CustomAuthenticationSuccessHandler(IAuthenticationFacade mi) {
        this.mi = mi;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String userName = authentication.getPrincipal() instanceof Principal ? ((Principal) authentication.getPrincipal()).getName() : ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Clientes cliente = this.mi.getBuscarConexion(userName);
        cliente.setImagen(mi.getImagen(path));
        request.getSession().setAttribute("__sess_cliente", cliente);
        if (cliente.getId_rol() == 1) { // es Administrativo
            List<Roles> roles = this.mi.getListarRolesCliente(cliente.getId_usuario());
            if (roles.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/login?error=3");
            }
            cliente.setRoles(roles);
            Roles aux = cliente.getRoles().getFirst();
            cliente.setId_rol(aux.getId_rol());
            cliente.setRol(aux.getRol());
        }
        if (cliente.getRoles().size() > 1) { // tiene mas de 1 rol
            request.getRequestDispatcher("/elegir-rol").forward(request, response);
        } else {
            mi.setParametrosClientesUsuario(cliente);
            Accesos acceso = mi.asignarAccesos(cliente);
            request.getSession().setAttribute("__sess_acceso", acceso); // Subimos los 'accesos' a la sesion
            request.getSession().setAttribute("__sess_cliente", cliente); // Subimos 'cliente' a la sesion
            if (cliente.getAlmacenes().size() > 1) { // tiene mas de 1 rol
                response.sendRedirect(request.getContextPath() + "/elegirAlmacen");
            }
            handle(request, response, authentication);
            clearAuthenticationAttributes(request);

        }
    }
    protected void handle(  HttpServletRequest request, HttpServletResponse response,  Authentication authentication ) throws IOException {
        String targetUrl = "/index";
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
