package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.CatalogoIndicadores;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface CatalogoIndicadoresDao {

    Integer saveCatalogoIndicadores(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    void deleteCatalogoIndicadores(Integer id_catalogo_indicador_pei) throws DataAccessException;

    List<CatalogoIndicadores> getAllCatalogoIndicadores() throws DataAccessException;

    CatalogoIndicadores getByid(int id_catalogo_indicador) throws DataAccessException;

    List<CatalogoIndicadores> getCatalogoIndicadoresByArticulacion(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    Integer getCantidadCatalogoIndicadoresByArticulacion(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    List<CatalogoIndicadores> getCatalogoIndicadoresByDenominacion(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    Integer getCantidadCatalogoIndicadoresByDenominacion(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    List<CatalogoIndicadores> getListaCatalalogosIndicadoresByAreasEstrategicas(Integer id_area_estrategica) throws DataAccessException;

    CatalogoIndicadores getItemCatalalogosIndicadoresByid(Integer id_catalogo_indicador_pei) throws DataAccessException;
}
