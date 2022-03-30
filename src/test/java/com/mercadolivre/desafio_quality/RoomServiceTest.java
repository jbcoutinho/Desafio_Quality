package com.mercadolivre.desafio_quality;

import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.service.RoomService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    private RoomService roomService = new RoomService();

    @Test
    public void shouldReturnTheAreaInM2(){
        Room room = new Room("sala", 10.0,20.0);
        assertEquals(200.0, roomService.calculaArea(room));
    }
}
