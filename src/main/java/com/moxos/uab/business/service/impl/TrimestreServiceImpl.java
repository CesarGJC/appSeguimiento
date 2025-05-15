package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.ITrimestreService;
import com.moxos.uab.domain.dto.response.view.ListView;
import com.moxos.uab.domain.entity.die.Trimestre;
import com.moxos.uab.persistence.die.TrimestreDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TrimestreServiceImpl implements ITrimestreService {
    private final TrimestreDao trimestreDao;

    @Override
    public List<ListView> getTrimestre(Integer id) {
        List<ListView> trimestresList = new ArrayList<>();
        trimestresList.add(new ListView("-1", "Seleccionar"));
        List<Trimestre> trimestresDescripcion = trimestreDao.getTrimestre(id);
        for (var item : trimestresDescripcion) {
            trimestresList.add(new ListView(item.getId_trimestre().toString(), item.getTrimestre().replace("_", " ")));
        }
        return trimestresList;
    }

    @Override
    public List<ListView> getTrimestrePorDescripcion(int idDescripcionOperacionesPoa) {
        List<ListView> trimestresList = new ArrayList<>();
        trimestresList.add(new ListView("-1", "Seleccionar"));
        List<Trimestre> trimestresDescripcion = trimestreDao.getListaTrimestrePorDescripcion(idDescripcionOperacionesPoa);
        for (var item : trimestresDescripcion) {
            trimestresList.add(new ListView(item.getId_trimestre().toString(), item.getTrimestre().replace("_", " ")));
        }
        return trimestresList;
    }


}
