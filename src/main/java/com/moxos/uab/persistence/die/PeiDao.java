package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Pei;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface PeiDao {
    Integer savePei(Pei pei) throws DataAccessException;

    void deletePei(Pei pei) throws DataAccessException;

    List<Pei> getAllPei() throws DataAccessException;

    List<Pei> getAllPeiByGestion(String gestion) throws DataAccessException;

    Pei getByid(int id_plan_pei) throws DataAccessException;

    List<Pei> getPeiByPei(Pei pei) throws DataAccessException;

    Integer getCantidadPeiByPei(Pei pei) throws DataAccessException;

    List<Pei> getPeiByGestion(Pei pei) throws DataAccessException;

    Integer getCantidadPeiByGestion(Pei pei) throws DataAccessException;
}
