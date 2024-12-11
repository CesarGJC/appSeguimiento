package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.AperturasProgramaticas;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface AperturasProgramaticasDao {
    Integer saveAperturasProgramaticas(AperturasProgramaticas aperturasProgramaticas) throws DataAccessException;

    void deleteAperturasProgramaticas(AperturasProgramaticas aperturasProgramaticas) throws DataAccessException;

    List<AperturasProgramaticas> getAllAperturasProgramaticas() throws DataAccessException;

    List<AperturasProgramaticas> getAllAperturasProgramaticasByCodigo(String Codigo) throws DataAccessException;

    List<AperturasProgramaticas> getAperturasProgramaticasByCodigo(AperturasProgramaticas aperturasProgramaticas) throws DataAccessException;

    Integer getCantidadAperturasProgramaticasByCodigo(AperturasProgramaticas aperturasProgramaticas) throws DataAccessException;

    AperturasProgramaticas getByid(int id_apertura_programatica) throws DataAccessException;

    List<AperturasProgramaticas> getAperturasProgramaticasByAperturasProgramaticas(AperturasProgramaticas aperturasProgramaticas) throws DataAccessException;

    Integer getCantidadAperturasProgramaticasByAperturasProgramaticas(AperturasProgramaticas aperturasProgramaticas) throws DataAccessException;

}
