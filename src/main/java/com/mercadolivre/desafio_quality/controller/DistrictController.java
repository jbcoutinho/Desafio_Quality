package com.mercadolivre.desafio_quality.controller;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.dto.DistrictDTO;
import com.mercadolivre.desafio_quality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {
    
    @Autowired
    private DistrictService service;
    
    @PostMapping("/district")
    public District create(@RequestBody DistrictDTO dto) {
        District district = DistrictDTO.parseToDistrict(dto);
        return service.save(district);
    }
    
}
