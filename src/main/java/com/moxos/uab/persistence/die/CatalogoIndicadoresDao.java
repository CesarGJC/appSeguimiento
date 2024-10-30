package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.CatalogoIndicadores;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface CatalogoIndicadoresDao {

    Integer saveCatalogoIndicadores(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    void deleteCatalogoIndicadores(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    List<CatalogoIndicadores> selectCatalogoIndicadores(int id_catalogo_indicador) throws DataAccessException;

    List<CatalogoIndicadores> getAllCatalogoIndicadores() throws DataAccessException;

    CatalogoIndicadores getByid(int id_catalogo_indicador) throws DataAccessException;

    List<CatalogoIndicadores> getCatalogoIndicadoresByIndicadores(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    Integer getCantidadCatalogoIndicadoresByIndicadores(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    List<CatalogoIndicadores> getCatalogoIndicadoresByCatalogo(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    Integer getCantidadCatalogoIndicadoresByCatalogo(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    List<CatalogoIndicadores> getCatalogoIndicadoresByMeta(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    Integer getCantidadCatalogoIndicadoresByMeta(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    List<CatalogoIndicadores> getCatalogoIndicadoresByLineaBase(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;

    Integer getCantidadCatalogoIndicadoresByLineaBase(CatalogoIndicadores catalogoIndicadores) throws DataAccessException;
}
