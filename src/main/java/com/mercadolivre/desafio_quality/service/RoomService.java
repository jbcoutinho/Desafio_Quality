package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    /**
     * Calcula a area de um comodo
     * @param room comodo para calculo da area
     * @return area calculada do comodo
     */
    public Double calculaArea(Room room) {
        return room.getRoomLength() * room.getRoomWidth();
    }

    /**
     * Soma a area total de uma lista de comodos
     * @param rooms lista de comodos para efetuar a soma
     * @return Double - area total de uma lista de comodos.
     */
    public Double sumRoomsArea(List<RoomDTO> rooms) {
        return rooms.stream().reduce(0.0, (acc, ele) -> ele.getArea() + acc, Double::sum);
    }

    /**
     * Percorre uma lista de comodos e retorna o que tem a maior area
     * @param rooms lista de quartos para avaliacao
     * @return Comodo com a maior area
     */
    public RoomDTO biggestArea(List<RoomDTO> rooms) {
        List<RoomDTO> roomsSortedByAreaDTOS = rooms.stream().sorted(Comparator.comparing(RoomDTO::getArea)).collect(Collectors.toList());

        return roomsSortedByAreaDTOS.get(rooms.size()-1);
    }
}
