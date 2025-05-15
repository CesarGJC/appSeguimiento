package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesFilterRequest;
import com.moxos.uab.domain.entity.die.OperacionesActividades;
import com.moxos.uab.domain.entity.die.Programas;
import com.moxos.uab.persistence.die.provider.OperacionesSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface OperacionesDao {

    @SelectProvider(type = OperacionesSqlProvider.class, method = "getFormulariosActividades")
    List<OperacionesActividades> getFormulariosActividades(@Param("filtro") FilterRequest<OperacionesFilterRequest> filtro, @Param("id_descripcion_operaciones_poa") Integer idDescripcion);

    @SelectProvider(type = OperacionesSqlProvider.class, method = "getCantidadByTipo")
    Integer getCantidadByTipo(@Param("filtro") FilterRequest<OperacionesFilterRequest> filtro, @Param("id_descripcion_operaciones_poa") Integer idDescripcion);

    Integer saveOperaciones(OperacionesActividades operacionesActividades) throws DataAccessException;

    void deleteOperaciones(OperacionesActividades operacionesActividades) throws DataAccessException;

    List<OperacionesActividades> getAllOperaciones() throws DataAccessException;

    OperacionesActividades getByid(int id_operaciones) throws DataAccessException;

    List<OperacionesActividades> getOperacionesByid(Integer id) throws DataAccessException;

    List<Programas> getProgramasPorFacultad(Integer id_facultad) throws DataAccessException;

    @SelectProvider(type = OperacionesSqlProvider.class, method = "getProgramaFacultad")
    Programas getProgramaFacultad(@Param("idFacultad") int idFacultad, @Param("idPrograma") int idPrograma);

    List<OperacionesActividades> getListaOperaciones(Integer id) throws DataAccessException;
}
