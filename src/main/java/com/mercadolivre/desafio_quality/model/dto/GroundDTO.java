package com.mercadolivre.desafio_quality.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundDTO {

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ]+", message = "O nome do cômodo deve começar com letra maiúscula.")
    private String propName;

    @Valid
    private DistrictDTO district;

    @Valid
    private List<RoomInputDTO> rooms;
}
