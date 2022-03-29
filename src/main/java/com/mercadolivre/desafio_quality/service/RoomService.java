package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    public Double calculaArea(Room room) {
        return room.getRoomLength() * room.getRoomWidth();
    }
}
