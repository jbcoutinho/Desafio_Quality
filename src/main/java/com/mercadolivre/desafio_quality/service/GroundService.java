package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GroundService {

    @Autowired
    RoomService roomService;

    public HashMap roomsArea(Ground ground) {
        HashMap<String, Double> objectObjectHashMap = new HashMap<>();
        for (Room room : ground.getRooms()) {
                objectObjectHashMap.put(room.getRoomName(), roomService.calculaArea(room));
            }
        return objectObjectHashMap;
    }
}
