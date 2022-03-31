package com.mercadolivre.desafio_quality.model.dto;

import com.mercadolivre.desafio_quality.model.Room;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RoomInputDTO {

    @NotBlank(message = "O nome do cômodo não pode ficar vazio.")
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ]+", message = "O nome do cômodo deve começar com letra maiúscula.")
    @Size(max = 30)
    private String roomName;

    @NotNull
    @Min(0)
    @Max(33)
    private Double roomLength;

    @NotNull
    @Min(0)
    @Max(25)
    private Double roomWidth;

    public static Room parseToRoom(RoomInputDTO dto) {
        return new Room(dto.getRoomName(), dto.getRoomLength(), dto.getRoomWidth());
    }

}
