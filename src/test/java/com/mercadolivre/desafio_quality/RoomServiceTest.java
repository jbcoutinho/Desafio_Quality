package com.mercadolivre.desafio_quality;

import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.service.GroundService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    private GroundService groundService = new GroundService();

    @Test
    public void shouldReturnTheAreaInM2(){
        Room room = new Room("sala", 10.0,20.0);
        assertEquals(200.0, groundService.calculaArea(room));
    }
}
