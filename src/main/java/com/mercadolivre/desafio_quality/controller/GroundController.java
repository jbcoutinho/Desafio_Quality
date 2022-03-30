package com.mercadolivre.desafio_quality.controller;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.service.GroundService;
import com.mercadolivre.desafio_quality.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class GroundController {

    @Autowired
    private GroundService service;

    @Autowired
    private RoomService roomService;

    /**
     * Calcula o valor de uma propriedade baseado na sua area e no seu bairro
     * @param ground - objeto com os dados da propriedade a ser avaliada
     * @return retorna o valor da propriedade
     */
    @PostMapping("/ground/value")
    public ResponseEntity<BigDecimal> groundValue(@RequestBody Ground ground) {
        return ResponseEntity.ok(service.groundValue(ground));
    }

    /**
     * Calcula a area de uma propriedade
     * @param ground - objeto com os dados da propriedade a ser avaliada
     * @return retorna a area da propriedade
     */
    @PostMapping("/ground/area")
    public ResponseEntity<Double> groundArea(@RequestBody Ground ground) {
        return ResponseEntity.ok(service.groundArea(ground));
    }

    /**
     * Calcula a area de todos os comodos de uma propriedade
     * @param ground - objeto com os dados da propriedade a ser avaliada
     * @return retorna a area de cada comodo da propriedade
     */
    @PostMapping("/ground/room/areas")
    public ResponseEntity<List<RoomDTO>> groundAreas(@RequestBody Ground ground) {
        return ResponseEntity.ok(service.getListRoomWithCalculatedArea(ground));
    }

    /**
    * Avalia o comodo com a maior area em uma propriedade
    * @param ground - objeto com os dados da propriedade a ser avaliada
    * @return Retorna um objeto com os dados do maior comodo em uma propriedade
    * */
    @PostMapping("/ground/room/biggest")
    public ResponseEntity<RoomDTO> biggestRoomArea(@RequestBody Ground ground) {
        return ResponseEntity.ok(roomService.biggestArea(service.getListRoomWithCalculatedArea(ground)));
    }
}
