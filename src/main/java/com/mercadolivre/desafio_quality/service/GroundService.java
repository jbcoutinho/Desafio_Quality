package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroundService {

    @Autowired
    RoomService roomService;

    public List<RoomDTO> getListRoomDTO(Ground ground) {
        List<RoomDTO> listRoomDTO =
                ground
                    .getRooms()
                    .stream()
                    .map(room -> new RoomDTO(room.getRoomName(), roomService.calculaArea(room)))
                    .collect(Collectors.toList());
        return listRoomDTO;
    }

    private Double calculateRoomsArea(List<RoomDTO> rooms) {
        return rooms.stream().reduce(0.0, (acc, ele) -> ele.getArea() + acc, Double::sum);
    }

    public BigDecimal groundValue(Ground ground) {
        List<RoomDTO> rooms = this.getListRoomDTO(ground);
        Double totalArea = calculateRoomsArea(rooms);

        return ground.getDistrict().getValueDistrictM2().multiply(BigDecimal.valueOf(totalArea));
    }

    public RoomDTO biggestArea(List<RoomDTO> rooms) {

        List<RoomDTO> sortedRoomsDTOS = rooms.stream().sorted(Comparator.comparing(RoomDTO::getArea)).collect(Collectors.toList());

        return sortedRoomsDTOS.get(rooms.size()-1);

//        List<Double> areas = rooms.values().stream().sorted().collect(Collectors.toList());
//        Double issoAaqui = areas.get(areas.size() -1);
//            for (String o : rooms.keySet()) {
//                if (rooms.get(o).equals(issoAaqui)) {
//                    return new RoomDTO(o, issoAaqui);
//                }
//            }
//            return null;
//        }
//        return null;
    }
}

