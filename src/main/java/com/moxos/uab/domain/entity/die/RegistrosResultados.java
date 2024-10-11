package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

@Data
public class RegistrosResultados extends Entidad {
    private int id_registro_resultado;
    private int gestion;
    private int programado;
    private int ejecutado;
    private String descripcion;
    private byte[] respaldo_doc;
    private Integer id_responsable_meta;
    private Byte[] respaldo_img;
}
