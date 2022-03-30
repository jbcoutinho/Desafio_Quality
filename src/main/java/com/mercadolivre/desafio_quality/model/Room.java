package com.mercadolivre.desafio_quality.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String roomName;

    @NotNull
    private Double roomLength;

    @NotNull
    private Double roomWidth;

    public Room(String roomName, Double roomLength, Double roomWidth) {
        this.roomName = roomName;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
    }
}
