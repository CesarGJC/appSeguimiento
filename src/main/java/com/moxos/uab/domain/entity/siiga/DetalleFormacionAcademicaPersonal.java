package com.moxos.uab.domain.entity.siiga;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleFormacionAcademicaPersonal {
    private Integer id_formacion;
    private Integer id_persona_kardex;
    private String expedido;
    private Date fechaemision;
    private Integer id_nivelestudio;
    private Integer id_profesiones;
    private String descripcion;
    private String url_formacion;
    private String tipotitulo;
    private String numerotitulo;
    private Boolean eseducacionsuperor;
    private String id_estado;
    private Boolean aprobado;
    private String nivelestudio;
    private String detalle_profesion;
    private Boolean mostrar;
}
