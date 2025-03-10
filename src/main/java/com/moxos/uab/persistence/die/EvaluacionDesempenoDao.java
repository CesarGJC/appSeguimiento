package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.EvaluacionDesempeno;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface EvaluacionDesempenoDao {

    Integer saveEvaluacionDesempeno(EvaluacionDesempeno evaluacionDesempeno) throws DataAccessException;

    void deleteEvaluacionDesempeno(EvaluacionDesempeno evaluacionDesempeno) throws DataAccessException;

    List<EvaluacionDesempeno> getAllEvalucionDesempeno() throws DataAccessException;

    EvaluacionDesempeno getByid(int id_evaluacion_desempeno) throws DataAccessException;

    List<EvaluacionDesempeno> getEvaluacionDesempeno(Integer id) throws DataAccessException;
}
