package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.List;

public interface ITrimestreService {
    List<ListView> getTrimestre(Integer id);

    List<ListView> getTrimestrePorDescripcion(int idDescripcionOperacionesPoa);

}
