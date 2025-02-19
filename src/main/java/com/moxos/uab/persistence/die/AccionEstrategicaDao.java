package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.AccionEstrategica;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface AccionEstrategicaDao {
    Integer saveAccionEstrategica(AccionEstrategica accionEstrategica) throws DataAccessException;

    void deleteAccionEstrategica(AccionEstrategica accionEstrategica) throws DataAccessException;

    AccionEstrategica getByid(int id_acciones_estrategica) throws DataAccessException;

    List<AccionEstrategica> getAcciones(Integer id) throws DataAccessException;

    AccionEstrategica getDetalleAccionesEstrategicas(int id_acciones_estrategica) throws DataAccessException;
}
