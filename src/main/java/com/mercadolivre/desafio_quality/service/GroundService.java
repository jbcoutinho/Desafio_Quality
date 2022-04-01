package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.exception.ResourceNotFoundException;
import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.DistrictDTO;
import com.mercadolivre.desafio_quality.model.dto.GroundDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomInputDTO;
import com.mercadolivre.desafio_quality.repository.GroundRepository;
import com.mercadolivre.desafio_quality.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroundService {

    private final DistrictService districtService;

    private final GroundRepository groundRepository;

    private final RoomRepository roomRepository;

    public GroundService(GroundRepository groundRepository, RoomRepository roomRepository, DistrictService districtService) {
        this.groundRepository = groundRepository;
        this.roomRepository = roomRepository;
        this.districtService = districtService;
    }

    /**
     * Recebe o ID de uma propriedade e chama o metodo para retornar a area calculada
     * @param groundID Um Long que representa um ID de um cômodo
     * @return Uma lista de comôdos
     */
    public List<RoomDTO> getRoomList(Long groundID) {
        return returnRoomList(groundID);
    }

    /**
     * Recebe o ID de uma propriedade e converte em uma lista de cômodos com sua area calculada
     * @param groundID - Um Long que é o ID da propriedade a ser avaliada
     * @return Uma lista com todos os cômodos de uma propriedade com sua área ja calculada
     */
    private List<RoomDTO> returnRoomList(Long groundID) {
        Ground ground = findById(groundID);

        return ground
                .getRooms()
                .stream()
                .map(room -> new RoomDTO(room.getRoomName(), calculateRoomArea(room)))
                .collect(Collectors.toList());
    }

    /**
     * Calcula o valor de uma propriedade baseado na sua area e no seu bairro
     * @param groundId - Um Long ID da propriedade
     * @return valor da propriedade
     */
    public String groundValue(Long groundId) {
        Ground ground = findById(groundId);
        List<RoomDTO> rooms = this.getRoomList(groundId);
        Double totalArea = sumRoomsArea(rooms);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(ground.getDistrict().getValueDistrictM2().multiply(BigDecimal.valueOf(totalArea)));
    }

    /**
     * Busca uma propriedade pelo ID
     * @param groundId Um Long ID de um propriedade
     * @return Uma propriedade
     */
    private Ground findById(Long groundId) {
        Optional<Ground> opt = groundRepository.findById(groundId);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("Imóvel nao encontrado na nossa base de dados!");
        }
        return opt.get();
    }

    /**
     * Calcula a área de uma propriedade
     * @param groundID - Um Long ID de um propriedade
     * @return A área da propriedade
     */
    public Double groundArea(Long groundID) {
        List<RoomDTO> listRoomDTO = returnRoomList(groundID);
        return sumRoomsArea(listRoomDTO);
    }

    /**
     * Salva a propriedade
     * @param dto Uma dto da propriedade
     * @return A propriedade que foi salva
     */
    public Ground save(GroundDTO dto) {
        List<Room> rooms = save(dto.getRooms().stream().map(RoomInputDTO::parseToRoom).collect(Collectors.toList()));

        District district = districtService.save(DistrictDTO.parseToDistrict(dto.getDistrict()));

        return groundRepository.save(new Ground(dto.getPropName(), district, rooms));
    }

    /**
     * Calcula a área de um cômodo
     * @param room cômodo para calculo da área
     * @return área calculada do cômodo
     */
    private Double calculateRoomArea(Room room) {
        return room.getRoomLength() * room.getRoomWidth();
    }

    /**
     * Soma a área total de uma lista de cômodos
     * @param rooms lista de cômodos para efetuar a soma
     * @return Double - área total de uma lista de cômodos.
     */
    private Double sumRoomsArea(List<RoomDTO> rooms) {
        return rooms.stream().reduce(0.0, (acc, ele) -> ele.getArea() + acc, Double::sum);
    }

    /**
     * Busca o cômodo com a maior área
     * @param rooms Uma lista de cômodos
     * @return O cômodo com a maior área
     */
    public RoomDTO biggestRoom(List<RoomDTO> rooms) {
        return biggestArea(rooms);
    }

    /**
     * Percorre uma lista de comodos e retorna o que tem a maior area
     * @param rooms lista de quartos para avaliacao
     * @return Comodo com a maior area
     */
    private RoomDTO biggestArea(List<RoomDTO> rooms) {
        List<RoomDTO> roomsSortedByAreaDTOS = rooms.stream().sorted(Comparator.comparing(RoomDTO::getArea)).collect(Collectors.toList());

        return roomsSortedByAreaDTOS.get(rooms.size()-1);
    }

    /**
     * Salva uma lista de cômodos
     * @param rooms Uma lista de cômodos
     * @return Uma lista de cômodos
     */
    public List<Room> save(List<Room> rooms) {
        return roomRepository.saveAll(rooms);
    }

}

