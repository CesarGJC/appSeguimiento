package com.moxos.uab.domain.entity.siiga;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Accesos implements Serializable {

    private int id_acceso;
    private String acceso;
    private List<Facultades> listaFacultades;
    private List<Programas> listaProgramas;
    private List<Departamentos> listaDepartamentos;
    private List<Planes> listaPlanes;

}