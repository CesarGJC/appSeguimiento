package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.ObjetivoEstrategia;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ObjetivoEstrategiaDao {
    Integer saveObjetivoEstrategia(ObjetivoEstrategia objetivoEstrategia) throws DataAccessException;

    void deleteObjetivoEstrategia(ObjetivoEstrategia objetivoEstrategia) throws DataAccessException;

    List<ObjetivoEstrategia> getAllCategoriaIndicador() throws DataAccessException;

    ObjetivoEstrategia getByid(int id_objetivo_estrategica) throws DataAccessException;

}
