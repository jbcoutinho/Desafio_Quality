package com.mercadolivre.desafio_quality;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.service.GroundService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GroundServiceTest {
 
	private GroundService groundService;
	
	@Test
	void shouldReturnRoomArea() {

		
	}
	
	private RoomDTO mockRoomDTO() {
		return new RoomDTO("any_name", 10.0);
	}
	
//	private Ground mockGround() {
//		return new Ground(
//			"any_name",
//			mockDistrict(),
//			mockRoomList()
//		);
//	}
//
//	private District mockDistrict() {
//		return new District(
//			"any_name",
//			BigDecimal.valueOf(1000)
//		);
//	}
//
//	private List<Room> mockRoomList() {
//
//	}
}
