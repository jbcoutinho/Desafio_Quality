package com.mercadolivre.desafio_quality.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroundDTO {
    private String propName;
    private DistrictDTO district;
    private List<RoomInputDTO> rooms;
}
