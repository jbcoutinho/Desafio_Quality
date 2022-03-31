package com.mercadolivre.desafio_quality.unit;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.DistrictDTO;
import com.mercadolivre.desafio_quality.model.dto.GroundDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomInputDTO;
import com.mercadolivre.desafio_quality.repository.GroundRepository;
import com.mercadolivre.desafio_quality.repository.RoomRepository;
import com.mercadolivre.desafio_quality.service.DistrictService;
import com.mercadolivre.desafio_quality.service.GroundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GroundServiceTest {

    private GroundService service;
    private final GroundRepository groundRepositoryMock = Mockito.mock(GroundRepository.class);
    private final RoomRepository roomRepositoryMock = Mockito.mock(RoomRepository.class);
    private final DistrictService districtServiceMock = Mockito.mock(DistrictService.class);

    @BeforeEach
    public void init() {
        service = new GroundService(groundRepositoryMock, roomRepositoryMock,  districtServiceMock);
    }

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
    void shouldReturnRoomsArea() {
        List<RoomDTO> roomList;

		Mockito.when(groundRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(createGround()));
        roomList = service.getRoomList(Mockito.anyLong());

        List<RoomDTO> list = createGround().getRooms().stream().map(room -> new RoomDTO(room.getRoomName(),
                        (room.getRoomLength() * room.getRoomWidth()))
                )
                .collect(Collectors.toList());

        assertEquals(list, roomList);
    }

	@Test
	void shouldReturnGroundArea() {
		Mockito.when(groundRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(mockGround()));
		Double area = service.groundArea(Mockito.anyLong());

		assertEquals(15.0, area);
	}

    public Ground createGround() {
        Room roomQuarto = Room.builder().roomName("Quarto").roomLength(Double.valueOf(10)).roomWidth(Double.valueOf(5)).build();
        Room roomSala = Room.builder().roomName("Sala").roomLength(Double.valueOf(5)).roomWidth(Double.valueOf(5)).build();

        List<Room> rooms = new ArrayList();
        rooms.add(roomQuarto);
        rooms.add(roomSala);

        District district = new District("melhor distrito", BigDecimal.valueOf(1200));

        return Ground.builder().propName("Casa Chique").rooms(rooms).district(district).build();
    }

    @Test
    public void verificaSeRetornaValorDoImovel() {
        Ground casaChique = createGround();
        Mockito.when(groundRepositoryMock.findById(Long.valueOf("1234"))).thenReturn(Optional.of(casaChique));

        String result = service.groundValue(Long.valueOf("1234"));

        assertEquals("90.000,00", result);
    }

    @Test
    public void verificaSeRetornaErrorAoConsultarValorDeImovelInexistente() {

        Mockito.when(groundRepositoryMock.findById(Long.valueOf("1234"))).thenReturn(Optional.empty());

        Throwable exception = assertThrows(RuntimeException.class,()-> service.groundValue(Long.valueOf("1234")));
        assertEquals("Imóvel nao encontrado na nossa base de dados!", exception.getMessage() );
    }

    @Test
    public void verificaSeRetornaMaiorArea() {

        Ground ground = createGround();
        List<RoomDTO> roomDTOS= new ArrayList<>();
        for (Room room: ground.getRooms()) {
            roomDTOS.add(new RoomDTO(room.getRoomName(), Double.valueOf(room.getRoomLength() * room.getRoomWidth())));
        }
        RoomDTO roomDTO = service.biggestRoom(roomDTOS);
        assertEquals(roomDTOS.get(0), roomDTO);
    }


    @Test
	public void verificaSeSalvaUmRoom() {
        Ground ground = createGround();
        DistrictDTO districtDTO = new DistrictDTO(ground.getDistrict().getPropDistrict(), ground.getDistrict().getValueDistrictM2());
        List<RoomInputDTO> roomInputDTO = new ArrayList<>();

        for (Room room: ground.getRooms()) {
            roomInputDTO.add(new RoomInputDTO(room.getRoomName(),room.getRoomLength(), room.getRoomWidth()));
        }

        GroundDTO groundDTO = new GroundDTO(ground.getPropName(), districtDTO, roomInputDTO);

        Mockito.when(roomRepositoryMock.saveAll(Mockito.anyList())).thenReturn(ground.getRooms());
        Mockito.when(districtServiceMock.save(Mockito.any())).thenReturn(ground.getDistrict());
        Mockito.when(groundRepositoryMock.save(Mockito.any())).thenReturn(ground);

        Ground save = service.save(groundDTO);

        assertEquals(ground, save);
    }
}