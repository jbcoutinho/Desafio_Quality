package com.mercadolivre.desafio_quality;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.repository.DistrictRepository;
import com.mercadolivre.desafio_quality.service.DistrictService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    private final DistrictRepository districtRepositoryMock = Mockito.mock(DistrictRepository.class);
    private DistrictService service;

    @BeforeEach
    public void init(){
        service = new DistrictService(districtRepositoryMock);
    }

    @Test
    public void verificaSeSalvaUmDistrito() {
        District district = new District("Melhor Distrito", BigDecimal.valueOf(900));
        Mockito.when(districtRepositoryMock.save(Mockito.any())).thenReturn(district);
        District result = service.save(new District());
        assertEquals(district, result);
    }
}
