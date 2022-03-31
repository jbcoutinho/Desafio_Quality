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
public Ground createGround() {
	Room roomQuarto = Room.builder().roomName("Quarto").roomLength(Double.valueOf(10)).roomWidth(Double.valueOf(5)).build();
	Room roomSala = Room.builder().roomName("Sala").roomLength(Double.valueOf(10)).roomWidth(Double.valueOf(5)).build();

	List<Room> rooms = new ArrayList();
	rooms.add(roomQuarto);
	rooms.add(roomSala);

	District district = new District("melhor distrito", BigDecimal.valueOf(1200));

	return Ground.builder().propName("Casa Chique").rooms(rooms).district(district).build();
}

	@Test
	public void verificaSeRetornaValorDoImovel() {
		GroundRepository mock = Mockito.mock(GroundRepository.class);

		Ground casaChique = createGround();
		GroundService service = new GroundService(mock);
		Mockito.when(mock.findById(Long.valueOf("1234"))).thenReturn(Optional.of(casaChique));

		BigDecimal result = service.groundValue(Long.valueOf("1234"));


		assertEquals(BigDecimal.valueOf(120000.0), result);
	}
}
