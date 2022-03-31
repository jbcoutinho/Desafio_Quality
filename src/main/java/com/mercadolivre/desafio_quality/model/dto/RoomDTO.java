package com.mercadolivre.desafio_quality.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    @NotBlank(message = "O nome do cômodo não pode ficar vazio.")
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ]+", message = "O nome do cômodo deve começar com letra maiúscula.")
    @Size(max = 30)
    private String name;
    private Double area;

}
