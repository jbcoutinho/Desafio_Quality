package com.mercadolivre.desafio_quality.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    private Double roomLength;
    private Double roomWidth;

    public Room(String roomName, Double roomLength, Double roomWidth) {
        this.roomName = roomName;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
    }
}
