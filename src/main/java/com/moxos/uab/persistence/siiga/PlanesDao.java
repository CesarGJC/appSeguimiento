package com.moxos.uab.persistence.siiga;

import com.moxos.uab.domain.entity.siiga.Facultades;
import com.moxos.uab.domain.entity.siiga.Planes;
import com.moxos.uab.domain.entity.siiga.Programas;
import com.moxos.uab.domain.entity.siiga.Universidades;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface PlanesDao {
    List<Planes> getUnvListarPlanes(Universidades universidad) throws DataAccessException;

    List<Planes> getFclListarPlanes(Facultades facultad) throws DataAccessException;
    List<Planes> getPrgListarPlanes(Programas programa) throws DataAccessException;
}
