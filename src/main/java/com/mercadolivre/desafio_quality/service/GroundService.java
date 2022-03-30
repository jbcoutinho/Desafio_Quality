package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.Room;
import com.mercadolivre.desafio_quality.model.dto.DistrictDTO;
import com.mercadolivre.desafio_quality.model.dto.GroundDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomInputDTO;
import com.mercadolivre.desafio_quality.repository.GroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroundService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private GroundRepository repository;

    /**
     * Recebe os dados de uma propriedade e converte em uma lista de comodos com sua area calculada
     * @param groundID - objeto com os dados da propriedade a ser avaliada
     * @return retorna uma lista com todos os comodos de uma propriedade com sua area ja calculada
     */
    public List<RoomDTO> getListRoomWithCalculatedArea(Long groundID) {

        Ground ground = findById(groundID);

        return ground
                .getRooms()
                .stream()
                .map(room -> new RoomDTO(room.getRoomName(), roomService.calculaArea(room)))
                .collect(Collectors.toList());
    }

    /**
     * Calcula o valor de uma propriedade baseado na sua area e no seu bairro
     * @param groundId - id da propriedade com os dados para avaliacao
     * @return retorna o valor da propriedade
     */
    public BigDecimal groundValue(Long groundId) {
        Ground ground = findById(groundId);
        List<RoomDTO> rooms = this.getListRoomWithCalculatedArea(groundId);
        Double totalArea = roomService.sumRoomsArea(rooms);

        return ground.getDistrict().getValueDistrictM2().multiply(BigDecimal.valueOf(totalArea));
    }

    private Ground findById(Long groundId) {
        Optional<Ground> opt = repository.findById(groundId);
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
        List<RoomDTO> listRoomDTO = getListRoomWithCalculatedArea(groundID);
        return roomService.sumRoomsArea(listRoomDTO);
    }

    public Ground save(GroundDTO dto) {
        List<Room> rooms = roomService.save(dto.getRooms().stream().map(room-> RoomInputDTO.parseToRoom(room)).collect(Collectors.toList()));
        District district = districtService.save(DistrictDTO.parseToDistrict(dto.getDistrict()));

        return repository.save( new Ground(dto.getPropName(), district, rooms));
    }
}

