package com.mercadolivre.desafio_quality;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.service.GroundService;
import com.mercadolivre.desafio_quality.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroundServiceTest {

    GroundService groundService = new GroundService();

    @Mock
    RoomService roomService;

//    @Test
//    public void deveRetornarUmaListaDeRooms() {
//        Mockito.when(roomService.calculaArea(Mockito.any(Room.class))).thenReturn(12.0);
//        Room room1 = new Room("cozinha", 15.0, 20.0);
//        Room room2 = new Room("escritorio", 40.0, 20.0);
//        Room room3 = new Room("sala", 25.0, 20.0);
//        Ground ground = new Ground();
//        List<Room> lista = new ArrayList<>();
//        lista.add(room1);
//        lista.add(room2);
//        lista.add(room3);
//        ground.setRooms(lista);
//        groundService.roomsArea(ground);
//
//    }
}
