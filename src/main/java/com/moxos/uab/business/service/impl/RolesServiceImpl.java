package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IRolesService;
import com.moxos.uab.domain.entity.siiga.Roles;
import com.moxos.uab.persistence.siiga.RolesDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements IRolesService {
    private final RolesDao rolesDao;

    public RolesServiceImpl(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    @Override
    public Roles getBuscarRolCliente(Roles rol) {
        return rolesDao.getBuscarRolCliente(rol);
    }

    @Override
    public List<Roles> getListarAlmacenesCliente(Roles rol) {
        return rolesDao.getListarAlmacenesCliente(rol);
    }
}
