package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.DistrictDTO;
import com.mercadolivre.desafio_quality.model.dto.GroundDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomInputDTO;
import com.mercadolivre.desafio_quality.repository.GroundRepository;
import com.mercadolivre.desafio_quality.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroundService {
    @Autowired
    private DistrictService districtService;

    @Autowired
    private GroundRepository groundRepository;

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Recebe os dados de uma propriedade e converte em uma lista de comodos com sua area calculada
     * @param groundID - objeto com os dados da propriedade a ser avaliada
     * @return retorna uma lista com todos os comodos de uma propriedade com sua area ja calculada
     */
    public List<RoomDTO> getArea(Long groundID) {
        return calculateArea(groundID);
    }

    private List<RoomDTO> calculateArea(Long groundID) {
        Ground ground = findById(groundID);

        return ground
                .getRooms()
                .stream()
                .map(room -> new RoomDTO(room.getRoomName(), calculaArea(room)))
                .collect(Collectors.toList());
    }

    /**
     * Calcula o valor de uma propriedade baseado na sua area e no seu bairro
     * @param groundId - id da propriedade com os dados para avaliacao
     * @return retorna o valor da propriedade
     */
    public BigDecimal groundValue(Long groundId) {
        Ground ground = findById(groundId);
        List<RoomDTO> rooms = this.getArea(groundId);
        Double totalArea = sumRoomsArea(rooms);

        return ground.getDistrict().getValueDistrictM2().multiply(BigDecimal.valueOf(totalArea));
    }

    private Ground findById(Long groundId) {
        Optional<Ground> opt = groundRepository.findById(groundId);
        if(opt.isEmpty()){
            throw new RuntimeException("imovel nao encontrado na nossa base de dados!");
        }
        return opt.get();
    }

    /**
     * Calcula a area de uma propriedade
     * @param groundID - objeto com os dados da propriedade a ser avaliada
     * @return retorna a area da propriedade
     */
    public Double groundArea(Long groundID) {
        List<RoomDTO> listRoomDTO = getArea(groundID);
        return sumRoomsArea(listRoomDTO);
    }

    public Ground save(GroundDTO dto) {
        List<Room> rooms = save(dto.getRooms().stream().map(RoomInputDTO::parseToRoom).collect(Collectors.toList()));

        District district = districtService.save(DistrictDTO.parseToDistrict(dto.getDistrict()));

        return groundRepository.save(new Ground(dto.getPropName(), district, rooms));
    }

    /**
     * Calcula a area de um comodo
     * @param room comodo para calculo da area
     * @return area calculada do comodo
     */
    private Double calculaArea(Room room) {
        return room.getRoomLength() * room.getRoomWidth();
    }

    /**
     * Soma a area total de uma lista de comodos
     * @param rooms lista de comodos para efetuar a soma
     * @return Double - area total de uma lista de comodos.
     */
    private Double sumRoomsArea(List<RoomDTO> rooms) {
        return rooms.stream().reduce(0.0, (acc, ele) -> ele.getArea() + acc, Double::sum);
    }

    public RoomDTO area(List<RoomDTO> rooms) {
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

    public List<Room> save(List<Room> rooms) {
        return roomRepository.saveAll(rooms);
    }

}

