package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface RolesDao {
    List<Roles> getListarRolesCliente(Roles rol) throws DataAccessException;

    List<Roles> getListarRolesCliente(Integer id_usuario) throws DataAccessException;

    Roles getBuscarRolCliente(Roles rol) throws DataAccessException;

    List<Roles> getListarAlmacenesCliente(Roles rol) throws DataAccessException;

    Roles getBuscarAlmacenCliente(Roles rol) throws DataAccessException;
}
