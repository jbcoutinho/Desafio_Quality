package com.mercadolivre.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String propDistrict;

    @NotNull
    private BigDecimal valueDistrictM2;

    public District(String propDistrict, BigDecimal valueDistrictM2) {
        this.propDistrict = propDistrict;
        this.valueDistrictM2 = valueDistrictM2;
    }
}
