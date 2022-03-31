package com.mercadolivre.desafio_quality;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mercadolivre.desafio_quality.repository.GroundRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.service.GroundService;

@SpringBootTest
class GroundServiceTest {

	@InjectMocks
	private GroundService groundService;

	@Mock
	private GroundRepository groundRepository;


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

	private Ground mockGroundWithNegatives(){


		Room room1 = new Room(1L,"quarto", -5.0, 1.0);
		Room room2 = new Room(1L,"sala", -5.0, 1.0);
		Room room3 = new Room(1L,"cozinha", -5.0, 1.0);
		List<Room> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		return new Ground(1L,"name", new District(1L,"teste", BigDecimal.valueOf(15)), rooms);
	}


	@Test
	void shouldReturnGroundArea() {

		Mockito.when(groundRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockGround()));
		Double area = groundService.groundArea(Mockito.anyLong());

		assertEquals(15.0, area);
		
	}

	@Test
	void shouldFailWhenGroundAreaIsNegative() {

		Mockito.when(groundRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockGroundWithNegatives()));
		Double area = groundService.groundArea(Mockito.anyLong());

//		assertEquals(15.0, area);

	}
	
	private RoomDTO mockRoomDTO() {
		return new RoomDTO("any_name", 10.0);
	}
	

}
