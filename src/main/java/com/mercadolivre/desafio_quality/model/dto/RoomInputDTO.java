package com.mercadolivre.desafio_quality.model.dto;

import com.mercadolivre.desafio_quality.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomInputDTO {

    @NotBlank(message = "O nome do cômodo não pode ficar vazio.")
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ]+", message = "O nome do cômodo deve começar com letra maiúscula.")
    @Size(max = 30, message = "O nome do cômodo deve ter no maximo 30 caracteres")
    private String roomName;

    @NotNull(message = "O comprimento não pode ficar vazio.")
    @Min(value = 0, message = "O valor minimo para o comprimento deve ser 0")
    @Max(value = 33, message = "O valor maximo para o comprimento deve ser 30")
    private Double roomLength;

    @NotNull(message = "A largura não pode ficar vazia.")
    @Min(value = 0, message = "O valor minimo para o largura deve ser 0")
    @Max(value = 25, message = "O valor maximo para o largura deve ser 25")
    private Double roomWidth;

    public static Room parseToRoom(RoomInputDTO dto) {
        return new Room(dto.getRoomName(), dto.getRoomLength(), dto.getRoomWidth());
    }

}
