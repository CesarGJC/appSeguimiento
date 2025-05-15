package com.moxos.uab.business.service;

import com.moxos.uab.domain.entity.siiga.Accesos;
import com.moxos.uab.domain.entity.siiga.Clientes;
import com.moxos.uab.domain.entity.siiga.Roles;

import java.util.List;

public interface IUserService {
    Clientes getBuscarConexion(String correo);

    List<Roles> getListarRolesCliente(Integer id_usuario);

    Accesos getBuscarAcceso(Clientes cliente);

    void setParametrosClientesUsuario(Clientes cliente);

    String getImagen();
}
