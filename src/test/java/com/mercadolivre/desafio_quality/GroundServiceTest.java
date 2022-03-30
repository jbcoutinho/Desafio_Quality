package com.mercadolivre.desafio_quality;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.repository.GroundRepository;
import com.mercadolivre.desafio_quality.service.GroundService;
import com.mercadolivre.desafio_quality.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class GroundServiceTest {

    @InjectMocks
    GroundService groundService;

    @Mock
    RoomService roomService;

    @Mock
    private GroundRepository repository;

    private Ground mockGround(){


        Room room1 = new Room(1L,"quarto", 5.0, 1.0);
        Room room2 = new Room(1L,"sala", 5.0, 1.0);
        Room room3 = new Room(1L,"cozinha", 5.0, 1.0);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        return new Ground(1L,"name", new District(1L,"teste", BigDecimal.valueOf(15)), rooms);
    }

    @Test
    public void deveDevolverAAreaDeUmaPropriedade() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(mockGround()));
        Mockito.when(roomService.calculaArea(any(Room.class))).thenReturn(10.0);
        Mockito.when(roomService.sumRoomsArea(any())).thenReturn(10.0);
        assertEquals(10.0, groundService.groundArea(10L));
    }

    @Test
    public void naoDeveDevolverAAreaDeUmaPropriedade() {
        Throwable exception = assertThrows(RuntimeException.class,()->groundService.groundArea(1L));
        assertEquals("imovel nao encontrado na nossa base de dados!", exception.getMessage() );
    }


}
