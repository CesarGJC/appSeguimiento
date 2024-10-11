package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IEnlacesService;
import com.moxos.uab.domain.entity.siiga.Enlaces;
import com.moxos.uab.persistence.siiga.EnlacesDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnlacesServiceImpl implements IEnlacesService {
    private final EnlacesDao enlacesDao;

    public EnlacesServiceImpl(EnlacesDao enlacesDao) {
        this.enlacesDao = enlacesDao;
    }

    @Override
    public List<Enlaces> getListarEnlaces(Enlaces enlace) throws DataAccessException {
        return enlacesDao.getListarEnlaces(enlace);
    }
}
