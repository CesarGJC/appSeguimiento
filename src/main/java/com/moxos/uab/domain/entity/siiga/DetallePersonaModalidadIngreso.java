package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetallePersonaModalidadIngreso {
    private Integer id_modalidadingreso;
    private Integer id_persona_kardex;
    private Integer id_programa;
    private String programa;
    private String modalidadingreso;
    private Date fechaingreso;
    private String url_modalidadingreso;
    private Boolean aprobado;
    private String id_estado;
    private Integer id_facultad;
    private Boolean mostrar;
}
