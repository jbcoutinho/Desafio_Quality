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

    @NotBlank(message = "O nome do bairro deve ser preenchido")
    @Size(max = 45, message = "O nome do bairro deve ter no maximo 45 caracteres")
    private String propDistrict;
    @NotNull(message = "O valor do metro quadrado deve ser preenchido")
    @Max(value = 13, message = "O valor do metro quadrado deve ter no maximo 13 caracteres")
    private BigDecimal valueDistrictM2;

    public static District parseToDistrict(DistrictDTO dto) {
        return new District(dto.getPropDistrict(), dto.getValueDistrictM2());
    }

}
