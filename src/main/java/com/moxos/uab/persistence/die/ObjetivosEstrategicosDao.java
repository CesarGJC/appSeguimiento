package com.moxos.uab.persistence.die;


import com.moxos.uab.domain.entity.die.ObjetivoEstrategicos;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ObjetivosEstrategicosDao {
    Integer saveObjetivosEstrategicos(ObjetivoEstrategicos objetivoEstrategicos) throws DataAccessException;

    void deleteObjetivosEstrategicos(ObjetivoEstrategicos objetivoEstrategicos) throws DataAccessException;

    List<ObjetivoEstrategicos> getAllObjetivosEstrategicos() throws DataAccessException;

    ObjetivoEstrategicos getByid(int id_objetivo_estrategica) throws DataAccessException;

    List<ObjetivoEstrategicos> getObjetivos(Integer id) throws DataAccessException;

}
