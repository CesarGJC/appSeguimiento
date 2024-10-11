package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Clientes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface ClientesDao  {
    Clientes getBuscarConexion(String correo) throws DataAccessException;
}
