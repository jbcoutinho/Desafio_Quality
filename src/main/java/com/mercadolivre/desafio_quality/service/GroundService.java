package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroundService {

    @Autowired
    RoomService roomService;

    public HashMap<String, Double> roomsArea(Ground ground) {
        HashMap<String, Double> objectObjectHashMap = new HashMap<>();
        for (Room room : ground.getRooms()) {
                objectObjectHashMap.put(room.getRoomName(), roomService.calculaArea(room));
            }
        return objectObjectHashMap;
    }

    private Double calculateArea(HashMap<String, Double> hashMap) {
        return hashMap.values().stream().reduce(0.0, (accumulator, element) -> accumulator + element);
    }

    public BigDecimal groundValue(Ground ground) {

        HashMap<String, Double> hashMap = this.roomsArea(ground);
        Double value = calculateArea(hashMap);

        return ground.getDistrict().getValueDistrictM2().multiply(BigDecimal.valueOf(value));

    }

    public RoomDTO biggestArea(HashMap<String, Double> rooms) {

        List<Double> areas = rooms.values().stream().sorted().collect(Collectors.toList());
        Double issoAaqui = areas.get(areas.size() -1);
            for (String o : rooms.keySet()) {
                if (rooms.get(o).equals(issoAaqui)) {
                    return new RoomDTO(o, issoAaqui);
                }
            }
            return null;
        }
    }

