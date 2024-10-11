package com.moxos.uab.business.service;

import com.moxos.uab.domain.entity.siiga.Roles;

import java.util.List;

public interface IRolesService {
    Roles getBuscarRolCliente(Roles rol);

    List<Roles> getListarAlmacenesCliente(Roles rol);
}
