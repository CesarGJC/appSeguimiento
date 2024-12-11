package com.moxos.uab.common.util;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static void toAssinateValue(Object objeto, String nombrePropiedad, Object valor) {
        if (objeto == null)
            return;
        try {
            // Obtener la clase del objeto
            Class<?> clase = objeto.getClass();
            // Iterar sobre todos los campos declarados
            for (Field campo : clase.getDeclaredFields()) {
                if (campo.getName().equals(nombrePropiedad)) {
                    campo.setAccessible(true); // Hacer accesible el campo privado
                    campo.set(objeto, valor); // Asignar el nuevo valor
                    return; // Salir después de encontrar y asignar
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
