package com.mercadolivre.desafio_quality;

import com.mercadolivre.desafio_quality.service.GroundService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesafioQualityApplicationTests {

    @InjectMocks
    private GroundService service;

    @Test
    void groundTest() {
        service.groundArea(10L);
    }

}
