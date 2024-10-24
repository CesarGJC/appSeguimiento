package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.AreaEstrategica;
import com.moxos.uab.domain.entity.die.PoliticasDesarrollo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
@Mapper
public interface PoliticasDesarrolloDao {
    Integer savePoliticasDesarrollo(PoliticasDesarrollo pd) throws DataAccessException;

    void deletePoliticasDesarrollo(PoliticasDesarrollo pd) throws DataAccessException;

    List<PoliticasDesarrollo> selectPoliticasDesarrollo(int id_politica_desarrollo) throws DataAccessException;

    List<PoliticasDesarrollo> getAllPoliticasDesarrollo() throws DataAccessException;

    PoliticasDesarrollo getByid(int id_politica_desarrollo) throws DataAccessException;

    List<PoliticasDesarrollo> getPoliticasDesarrolloByArea(PoliticasDesarrollo politicasDesarrollo) throws DataAccessException;

    Integer getCantidadPoliticasDesarrolloByArea(PoliticasDesarrollo politicasDesarrollo) throws DataAccessException;

    List<PoliticasDesarrollo> getPoliticasDesarrolloByPolitica(PoliticasDesarrollo politicasDesarrollo) throws DataAccessException;

    Integer getCantidadPoliticasDesarrolloByPolitica(PoliticasDesarrollo politicasDesarrollo) throws DataAccessException;

    List<PoliticasDesarrollo> getAllPoliticasDesarrolloPorArea(Integer id_area_estrategica);
}

