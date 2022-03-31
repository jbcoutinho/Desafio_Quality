package com.mercadolivre.desafio_quality.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @NotNull
    private String roomName;

    @NotNull
    private Double roomLength;

    @NotNull
    private Double roomWidth;
}
