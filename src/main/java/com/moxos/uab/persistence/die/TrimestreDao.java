package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Trimestre;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TrimestreDao {
    List<Trimestre> getTrimestre(Integer id) throws DataAccessException;

    List<Trimestre> getTrimestreProgramadosYEejecutados(Integer id) throws DataAccessException;
    List<Trimestre> getListaTrimestrePorDescripcion(Integer id) throws DataAccessException;
}
