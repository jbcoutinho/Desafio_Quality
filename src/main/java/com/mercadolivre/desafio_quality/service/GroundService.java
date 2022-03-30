package com.mercadolivre.desafio_quality.service;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroundService {

    @Autowired
    private RoomService roomService;

    /**
     * Recebe os dados de uma propriedade e converte em uma lista de comodos com sua area calculada
     * @param ground - objeto com os dados da propriedade a ser avaliada
     * @return retorna uma lista com todos os comodos de uma propriedade com sua area ja calculada
     */
    public List<RoomDTO> getListRoomWithCalculatedArea(Ground ground) {
        return ground
                .getRooms()
                .stream()
                .map(room -> new RoomDTO(room.getRoomName(), roomService.calculaArea(room)))
                .collect(Collectors.toList());
    }

    /**
     * Calcula o valor de uma propriedade baseado na sua area e no seu bairro
     * @param ground - objeto com os dados da propriedade a ser avaliada
     * @return retorna o valor da propriedade
     */
    public BigDecimal groundValue(Ground ground) {
        List<RoomDTO> rooms = this.getListRoomWithCalculatedArea(ground);
        Double totalArea = roomService.sumRoomsArea(rooms);

        return ground.getDistrict().getValueDistrictM2().multiply(BigDecimal.valueOf(totalArea));
    }
    /**
     * Calcula a area de uma propriedade
     * @param ground - objeto com os dados da propriedade a ser avaliada
     * @return retorna a area da propriedade
     */
    public Double groundArea(Ground ground) {
        List<RoomDTO> listRoomDTO = getListRoomWithCalculatedArea(ground);
        return roomService.sumRoomsArea(listRoomDTO);
    }
}

