package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.UnidadEncargada;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UnidadEncargadaDao {
    void saveUnidadEncargada(UnidadEncargada unidadEncargada) throws DataAccessException;

    void updateUnidadEncargada(UnidadEncargada unidadEncargada) throws DataAccessException;

    void deleteUnidadEncargada(UnidadEncargada unidadEncargada) throws DataAccessException;

    List<UnidadEncargada> selectUnidadEncargada(int id_unidad_encargada) throws DataAccessException;

    UnidadEncargada getByid(int id_unidad_encargada) throws DataAccessException;
}
