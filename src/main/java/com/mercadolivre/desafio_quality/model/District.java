package com.mercadolivre.desafio_quality.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class District {

    @Valid
    private List<Ground> grounds;

    @NotNull
    private String propDistrict;

    @NotNull
    private BigDecimal valueDistrictM2;
}
