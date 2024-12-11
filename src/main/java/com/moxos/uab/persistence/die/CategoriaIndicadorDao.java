package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.CategoriaIndicador;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface CategoriaIndicadorDao {
    Integer saveCategoriaIndicador(CategoriaIndicador categoriaIndicador) throws DataAccessException;

    void deleteCategoriaIndicador(CategoriaIndicador categoriaIndicador) throws DataAccessException;

    List<CategoriaIndicador> getAllCategoriaIndicador() throws DataAccessException;

    CategoriaIndicador getByid(int id_categoria) throws DataAccessException;

    List<CategoriaIndicador> getCategoriaiIndicadorByDescripcion(CategoriaIndicador categoriaIndicador) throws DataAccessException;

    Integer getCantidadCategoriaIndicadorByDescripcion(CategoriaIndicador categoriaIndicador) throws DataAccessException;

    List<CategoriaIndicador> getCategoriaiIndicadorByAbreviacion(CategoriaIndicador categoriaIndicador) throws DataAccessException;

    Integer getCantidadCategoriaIndicadorByAbreviacion(CategoriaIndicador categoriaIndicador) throws DataAccessException;
}
