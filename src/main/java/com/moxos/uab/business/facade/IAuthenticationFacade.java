package com.moxos.uab.business.facade;

import com.moxos.uab.domain.entity.siiga.*;

import java.util.List;

public interface IAuthenticationFacade {
    Clientes getBuscarConexion(String correo);

    List<Roles> getListarRolesCliente(Integer id_usuario);

    Accesos asignarAccesos(Clientes cliente);

    void setParametrosClientesUsuario(Clientes cliente);

    String getImagen();

    Clientes getBuscarRolCliente(Integer id_rol, Clientes cliente);

    String getMenuSistema(Clientes cliente);
}
