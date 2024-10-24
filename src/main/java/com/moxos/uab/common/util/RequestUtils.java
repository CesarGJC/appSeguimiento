package com.moxos.uab.common.util;

import com.moxos.uab.domain.dto.request.general.SelectListItemDto;

import java.util.ArrayList;
import java.util.List;

public class RequestUtils {
    public static List<SelectListItemDto> getCantidadDeElementos() {
        List<SelectListItemDto> moxstrarelementos = new ArrayList<>();
        moxstrarelementos.add(new SelectListItemDto("50", "50"));
        moxstrarelementos.add(new SelectListItemDto("80", "80"));
        moxstrarelementos.add(new SelectListItemDto("100", "100"));
        return moxstrarelementos;
    }
}
