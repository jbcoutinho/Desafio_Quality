package com.mercadolivre.desafio_quality.controller;

import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.model.dto.RoomDTO;
import com.mercadolivre.desafio_quality.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class GroundController {
    @Autowired
    GroundService service;

    @PostMapping("/ground")
    public ResponseEntity<BigDecimal> groundValue(@RequestBody Ground ground) {
        return ResponseEntity.ok(service.groundValue(ground));
    }

    @PostMapping("/ground/biggest")
    public ResponseEntity<RoomDTO> biggestArea(@RequestBody Ground ground) {
        return ResponseEntity.ok(service.biggestArea(service.getListRoomDTO(ground)));
    }
}
