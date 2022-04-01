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
import java.util.List;

@RestController
public class GroundController {

    @Autowired
    private GroundService groundService;

    /**
     * Calcula o valor de uma propriedade baseado na sua Área e no seu bairro
     * @param groundID - Long ID da propriedade
     * @return O valor da propriedade com status 200 OK
     */
    @GetMapping("/ground/{groundID}/value")
    public ResponseEntity<String> groundValue(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.groundValue(groundID));
    }

    /**
     * Calcula a área de uma propriedade
     * @param groundID - Long com o ID da propriedade a ser avaliada
     * @return retorna a área da propriedade com status 200 OK
     */
    @GetMapping("/ground/{groundID}/area")
    public ResponseEntity<Double> groundArea(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.groundArea(groundID));
    }

    /**
     * Calcula a área de todos os cômodos de uma propriedade
     * @param groundID - Long com o ID da propriedade a ser avaliada
     * @return A area de cada cômodo da propriedade com status 200 OK
     */
    @GetMapping("/ground/{groundID}/room/areas")
    public ResponseEntity<List<RoomDTO>> groundAreas(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.getRoomList(groundID));
    }

    /**
    * Avalia o cômodo com a maior área em uma propriedade
    * @param groundID - Long com o ID da propriedade a ser avaliada
    * @return Um objeto com os dados do maior comodo em uma propriedade com status 200 OK
    * */
    @GetMapping("/ground/{groundID}/room/biggest")
    public ResponseEntity<RoomDTO> biggestRoomArea(@PathVariable Long groundID) {
        return ResponseEntity.ok(groundService.biggestRoom(groundService.getRoomList(groundID)));
    }


    /**
     * Cadastra uma nova propriedade
     * @param ground Um objeto com todos os dados da propriedade a ser cadastrada
     * @return O objeto que foi cadastrado com o status 201 created
     */
    @PostMapping("/ground")
    public ResponseEntity<Ground> create(@RequestBody @Valid GroundDTO ground) {
        return new ResponseEntity<>(groundService.save(ground), HttpStatus.CREATED );
    }
}
