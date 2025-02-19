package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Permisos;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;


@Mapper
public interface PermisosDao {
    Integer savePermisos(Permisos permisos) throws DataAccessException;

    void deletePermisos(Permisos permisos) throws DataAccessException;

    Permisos getByid (Integer id_permisos) throws DataAccessException;

}
