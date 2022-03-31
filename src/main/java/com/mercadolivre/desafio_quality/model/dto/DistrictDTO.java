package com.mercadolivre.desafio_quality.model.dto;

import com.mercadolivre.desafio_quality.model.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {

    @NotBlank
    @Size(max = 45)
    private String propDistrict;
    @NotNull
    @Max(13)
    private BigDecimal valueDistrictM2;

    public static District parseToDistrict(DistrictDTO dto) {
        return new District(dto.getPropDistrict(), dto.getValueDistrictM2());
    }

}
