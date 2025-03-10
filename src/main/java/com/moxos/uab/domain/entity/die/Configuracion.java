package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class Configuracion extends Entidad {
    private int id_configuracion;
    private String clave;
    private String valor;
    private String etiqueta;
    private int tipo_valor;
}
