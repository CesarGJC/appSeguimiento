package com.moxos.uab.business.service;

import com.moxos.uab.domain.entity.siiga.Enlaces;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface IEnlacesService {
    List<Enlaces> getListarEnlaces(Enlaces enlace) throws DataAccessException;
}
