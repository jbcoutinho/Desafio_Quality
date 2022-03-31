package com.mercadolivre.desafio_quality.controller;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.dto.GroundDTO;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class GroundController {

    @Autowired
    private GroundService groundService;

    /**
     * Calcula o valor de uma propriedade baseado na sua area e no seu bairro
     * @param groundID - id do objeto com os dados da propriedade a ser avaliada
     * @return retorna o valor da propriedade
     */
    @GetMapping("/ground/{groundID}/value")
    public ResponseEntity<BigDecimal> groundValue(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.groundValue(groundID));
    }

    /**
     * Calcula a area de uma propriedade
     * @param groundID - objeto com os dados da propriedade a ser avaliada
     * @return retorna a area da propriedade
     */
    @GetMapping("/ground/{groundID}/area")
    public ResponseEntity<Double> groundArea(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.groundArea(groundID));
    }

    /**
     * Calcula a area de todos os comodos de uma propriedade
     * @param groundID - objeto com os dados da propriedade a ser avaliada
     * @return retorna a area de cada comodo da propriedade
     */
    @GetMapping("/ground/{groundID}/room/areas")
    public ResponseEntity<List<RoomDTO>> groundAreas(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.getRoomList(groundID));
    }

    /**
    * Avalia o comodo com a maior area em uma propriedade
    * @param groundID - objeto com os dados da propriedade a ser avaliada
    * @return Retorna um objeto com os dados do maior comodo em uma propriedade
    * */
    @GetMapping("/ground/{groundID}/room/biggest")
    public ResponseEntity<RoomDTO> biggestRoomArea(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.biggestRoom(groundService.getRoomList(groundID)));
    }

    @PostMapping("/ground")
    public ResponseEntity<Ground> create(@RequestBody @Valid GroundDTO ground) {
        return new ResponseEntity<>(groundService.save(ground), HttpStatus.CREATED );
    }
}
