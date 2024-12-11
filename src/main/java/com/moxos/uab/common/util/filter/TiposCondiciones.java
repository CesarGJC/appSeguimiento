package com.moxos.uab.common.util.filter;

import com.moxos.uab.domain.dto.response.view.ListView;

import java.util.ArrayList;
import java.util.List;

public class TiposCondiciones {
    private static List<ListView> getListaCondiciones(Integer tipo) {
        switch (tipo) {
            case 0:
                return tiposCondicionesString();
            case 1:
                return tiposCondicionesNumber();
        }
        return null;
    }

    private static List<ListView> tiposCondicionesString() {
        List<ListView> tiposCondiciones = new ArrayList<>();
        tiposCondiciones.add(new ListView("0", "IGUAL"));
        tiposCondiciones.add(new ListView("1", "DIFERENTE"));
        tiposCondiciones.add(new ListView("2", "CONTIENE"));
        return tiposCondiciones;
    }

    private static List<ListView> tiposCondicionesNumber() {
        List<ListView> tiposCondiciones = new ArrayList<>();
        tiposCondiciones.add(new ListView("0", "IGUAL"));
        tiposCondiciones.add(new ListView("1", "DIFERENTE"));
        tiposCondiciones.add(new ListView("3", "MAYOR"));
        tiposCondiciones.add(new ListView("4", "MAYOR IGUAL QUE"));
        tiposCondiciones.add(new ListView("5", "MENOR QUE"));
        tiposCondiciones.add(new ListView("6", "MENOR IGUAL QUE"));
        return tiposCondiciones;
    }
}
