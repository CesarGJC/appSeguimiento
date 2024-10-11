package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.ResponsableMeta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
@Mapper
public interface ResponsableMetaDao {
    void saveResponsableMeta(ResponsableMeta responsableMeta) throws DataAccessException;

    void updateResponsableMeta(ResponsableMeta responsableMeta) throws DataAccessException;

    void deleteResponsableMeta(ResponsableMeta responsableMeta) throws DataAccessException;

    List<ResponsableMeta> selectResponsableMetas(int id_responsable_meta) throws DataAccessException;

    ResponsableMeta getByid(int id_responsable_meta) throws DataAccessException;
}

