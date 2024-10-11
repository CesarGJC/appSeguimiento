package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleIdiomaPersonal {
    private Integer id_idioma;
    private String descripcion_idioma;
    private Boolean lee;
    private Boolean escribe;
    private Integer id_persona_kardex;
    private String url_idioma;
    private Boolean aprobado;
    private String id_estado;
    private String tipo_idioma;
    private Boolean mostrar;
}
