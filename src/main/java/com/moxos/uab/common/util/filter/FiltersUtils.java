package com.moxos.uab.common.util.filter;

import com.moxos.uab.domain.dto.request.general.FilterParamsRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FiltersUtils {
    public static List<FilterParamsRequest> setValue(List<FilterParamsRequest> filters, FilterParamsRequest filter) {
        List<FilterParamsRequest> results = new ArrayList<>();
        if (!filters.stream().anyMatch(paramsRequest -> paramsRequest.getOption().equals(filter.getOption()))) {
            if (filter.getOption() != null)
                filters.add(filter);
            return filters;
        }
        results = filters.stream()
                .map(paramsRequest -> paramsRequest.getOption().equals(filter.getOption()) ? filter : paramsRequest)
                .collect(Collectors.toList());
        return results;
    }

    public static String condiciones(int value) {
        switch (value) {
            case 0:
                return "%s = %s";
            case 1:
                return "%s != %s";
            case 2:
                return "%s LIKE CONCAT('|', %s, '|')";
            case 3:
                return "%s > %s";
            case 4:
                return "%s >= %s";
            case 5:
                return "%s < %s";
            case 6:
                return "%s <= %s";
        }
        return "";
    }
}
