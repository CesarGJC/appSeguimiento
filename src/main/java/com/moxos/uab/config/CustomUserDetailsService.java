package com.moxos.uab.config;

import com.moxos.uab.business.facade.IAuthenticationFacade;
import com.moxos.uab.domain.entity.siiga.Clientes;
import com.moxos.uab.domain.entity.siiga.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final IAuthenticationFacade mi;

    public CustomUserDetailsService(IAuthenticationFacade mi) {
        this.mi = mi;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clientes cliente = this.mi.getBuscarConexion(username);
        if ((cliente == null) || (cliente.getId_rol() != 1)) {
            throw new UsernameNotFoundException("No se econtro al usuario: " + username);
        } else {
            List<GrantedAuthority> roles = new ArrayList<>();
            List<Roles> usuariosroles = this.mi.getListarRolesCliente(cliente.getId_rol());
            usuariosroles.forEach(usuariorol -> {
                roles.add(new SimpleGrantedAuthority(usuariorol.getRol()));
            });
            return new User(cliente.getCorreo(), cliente.getClave(), roles);
        }
    }
}
