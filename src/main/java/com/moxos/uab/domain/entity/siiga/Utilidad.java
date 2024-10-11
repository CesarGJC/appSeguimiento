package com.moxos.uab.domain.entity.siiga;

public class Utilidad extends Principal {

    public String getMensaje(int codigoError) {

        switch (codigoError) {
            case 0:
                return "LOS DATOS SE INSERTARON CORRECTAMENTE";
            case 1:
                return "PRIMARIA DUPLICADA";
            case 2:
                return "NUMERO DIVIDIDO ENTRE CERO";
            case 3:
                return "ERROR DE SINTAXIS";
            case 4:
                return "VIOLACIoN DE LLAVE FORANEA";
            case 5:
                return "EXISTEN MAS COLUMNAS PARA LA CANTIDAD DE ARGUMENTOS";
            default:
                return "ALGUN ERROR EN LA INSERCION";
        }
    }
}
