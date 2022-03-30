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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class GroundServiceTest {

    @InjectMocks
    GroundService groundService;

//    @Mock
//    RoomService roomService;

    @Mock
    private GroundRepository repository;

    private List<RoomDTO> mockGround(){
        RoomDTO room1 = new RoomDTO("quarto", 5.0);
        RoomDTO room2 = new RoomDTO("sala", 5.0);
        RoomDTO room3 = new RoomDTO("cozinha", 5.0);
        List<RoomDTO> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        return rooms;
    }

    @Test
    public void deveDevolverAAreaDeUmaPropriedade() {
        Mockito.when(groundService.getListRoomWithCalculatedArea(Mockito.anyLong())).thenReturn(mockGround());
        GroundService groundService = new GroundService(mockGround());
        assertEquals(25.0, groundService.groundArea(10L));
    }


}
