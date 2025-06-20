package com.lukaszwodniak.samochodowo.models.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * CarSearchParams
 * <br>
 * Created on: 2025-06-22
 * @author Łukasz Wodniak
 */

@Getter
@Setter
public class CarSearchParams {
    private int productionYearFrom;
    private int productionYearTo;
}
