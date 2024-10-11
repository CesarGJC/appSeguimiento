package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.RegistrosResultados;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface RegistrosResultadosDao {
    void saveRegistrosResultados(RegistrosResultados registrosResultados) throws DataAccessException;

    void updateRegistroResultados(RegistrosResultados registrosResultados) throws DataAccessException;

    void deleteRegistroResultados(RegistrosResultados registrosResultados) throws DataAccessException;

    List<RegistrosResultados> selectRegistroResultados(int id_registro_resultado) throws DataAccessException;

    RegistrosResultados getByid(int id_registro_resultado) throws DataAccessException;
}
