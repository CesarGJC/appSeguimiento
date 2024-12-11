package com.moxos.uab.domain.dto.request.general;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FilterRequest<TFilter> {
    private TFilter filter;
    private List<FilterParamsRequest> params = new ArrayList<>();
}
