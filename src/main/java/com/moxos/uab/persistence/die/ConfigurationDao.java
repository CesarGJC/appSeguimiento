package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Configuracion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ConfigurationDao {

    List<Configuracion> getConfiguracion() throws DataAccessException;

    Configuracion getConfiguracionPorClave(String clave) throws DataAccessException;

    void saveConfiguracion(Configuracion configuracion) throws DataAccessException;

    String getValor (String clave) throws DataAccessException;
}
