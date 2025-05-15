package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.AccionCortoPlazoPoa;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface AccionCortoPlazoPoaDao {
    Integer saveAccionCortoPlazoPoa(AccionCortoPlazoPoa accionCortoPlazoPoa) throws DataAccessException;

    void deleteAccionCortoPlazoPoa(AccionCortoPlazoPoa accionCortoPlazoPoa) throws DataAccessException;

    AccionCortoPlazoPoa getByid(int id_accion_corto_plazo_poa) throws DataAccessException;

    List<AccionCortoPlazoPoa> getAccionesCortoPlazo(int id_objetivos_gestion_poa) throws DataAccessException;

    AccionCortoPlazoPoa getDetalleAccionesCortoPlazo(int id_accion_corto_plazo_poa) throws DataAccessException;
}
