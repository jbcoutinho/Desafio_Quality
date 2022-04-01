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

    @NotBlank(message = "O nome do bairro deve ser preenchido")
    @Size(max = 30, message = "O nome da propriedade deve ter no maximo 30 caracteres")
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ]+", message = "O nome da propriedade deve começar com letra maiúscula.")
    private String propName;

    @Valid
    private DistrictDTO district;

    @Valid
    private List<RoomInputDTO> rooms;
}
