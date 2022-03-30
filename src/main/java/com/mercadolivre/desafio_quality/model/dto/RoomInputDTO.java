package com.mercadolivre.desafio_quality.model.dto;

import com.mercadolivre.desafio_quality.model.Room;
import lombok.Data;

@Data
public class RoomInputDTO {

    private String roomName;
    private Double roomLength;
    private Double roomWidth;

    public static Room parseToRoom(RoomInputDTO dto) {
        return new Room(dto.getRoomName(), dto.getRoomLength(), dto.getRoomWidth());
    }

}
