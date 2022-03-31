package com.mercadolivre.desafio_quality.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundDTO {
    private String propName;
    private DistrictDTO district;
    private List<RoomInputDTO> rooms;
}
