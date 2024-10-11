package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Enlaces;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface EnlacesDao {
    List<Enlaces> getListarEnlaces(Enlaces enlace) throws DataAccessException;

    Enlaces getEnlBuscarEnlace(Enlaces enlace) throws DataAccessException;

    List<Enlaces> getStdListarEstadosTabla(Enlaces enlace) throws DataAccessException;
}