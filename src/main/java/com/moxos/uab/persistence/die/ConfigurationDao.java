package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.entity.die.Configuracion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigurationDao {
    Configuracion getConfiguracionActual();
}
