package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.PoliticasDesarrollo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
@Mapper
public interface PoliticasDesarrolloDao {
    void savePoliticasDesarrollo(PoliticasDesarrollo pd) throws DataAccessException;

    void updatePoliticasDesarrollo(PoliticasDesarrollo pd) throws DataAccessException;

    void deletePoliticasDesarrollo(PoliticasDesarrollo pd) throws DataAccessException;

    List<PoliticasDesarrollo> selectPoliticasDesarrollo(int id_politica_desarrollo) throws DataAccessException;

    List<PoliticasDesarrollo> getAllPoliticasDesarrollo() throws DataAccessException;

    PoliticasDesarrollo getByid(int id_politica_desarrollo) throws DataAccessException;
}
