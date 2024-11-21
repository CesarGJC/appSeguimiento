package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.AreaEstrategica;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface AreaEstrategicaDao {
    Integer saveAreaEstrategica(AreaEstrategica areaEstrategica) throws DataAccessException;

    void deleteAreaEstrategica(AreaEstrategica areaEstrategica) throws DataAccessException;

    List<AreaEstrategica> selectAreaEstrategica(int id_area_estrategica) throws DataAccessException;

    List<AreaEstrategica> getAllAreaEstrategica() throws DataAccessException;

    List<AreaEstrategica> getAllAreaEstrategicaPorGestion(String gestion) throws DataAccessException;

    List<AreaEstrategica> getAllAreaEstrategicaPorCodigo(String codigo) throws DataAccessException;

    AreaEstrategica getByid(int id_area_estrategica) throws DataAccessException;

    List<AreaEstrategica> getAreasEstrategicasByArea(AreaEstrategica areaEstrategica) throws DataAccessException;

    Integer getCantidadAreasEstrategicasByArea(AreaEstrategica areaEstrategica) throws DataAccessException;

    List<AreaEstrategica> getAreasEstrategicasByGestion(AreaEstrategica areaEstrategica) throws DataAccessException;

    Integer getCantidadAreasEstrategicasByGestion(AreaEstrategica areaEstrategica) throws DataAccessException;

    List<AreaEstrategica> getAreasEstrategicasByCodigo(AreaEstrategica areaEstrategica) throws DataAccessException;

    Integer getCantidadAreasEstrategicasByCodigo(AreaEstrategica areaEstrategica) throws DataAccessException;
}
