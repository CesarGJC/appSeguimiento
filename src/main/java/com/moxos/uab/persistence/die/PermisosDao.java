package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Permisos;
import com.moxos.uab.persistence.die.provider.FormularioSqlProvider;
import com.moxos.uab.persistence.die.provider.PermisosSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;


@Mapper
public interface PermisosDao {

    Integer savePermisos(Permisos permisos) throws DataAccessException;

    void habilitarPermisos(Permisos permisos) throws DataAccessException;

    void deletePermisos(Permisos permisos) throws DataAccessException;

    Permisos getByid(Integer id_permisos) throws DataAccessException;

    @SelectProvider(type = PermisosSqlProvider.class, method = "getListaUnidadesAdministrativas")
    List<Permisos> getListaUnidadesAdministrativas(Integer idTipoUnidad, Integer idFormulario) throws DataAccessException;
}
