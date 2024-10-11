package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.AreaEstrategica;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface AreaEstrategicaDao {
    void saveAreaEstrategica(AreaEstrategica areaEstrategica) throws DataAccessException;

    void updateAreaEstrategica(AreaEstrategica areaEstrategica) throws DataAccessException;

    void deleteAreaEstrategica(AreaEstrategica areaEstrategica) throws DataAccessException;

    List<AreaEstrategica> selectAreaEstrategica(int id_area_estrategica) throws DataAccessException;

    List<AreaEstrategica> getAllAreaEstrategica() throws DataAccessException;

    AreaEstrategica getByid(int id_area_estrategica) throws DataAccessException;
}
