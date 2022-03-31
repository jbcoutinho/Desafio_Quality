package com.mercadolivre.desafio_quality;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.repository.GroundRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroundRepository repository;

    @Test
    public void shouldCreateAGroundAndReturn201() throws Exception {

        MvcResult res = mockMvc
                .perform(MockMvcRequestBuilders.post("/ground")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObject()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String content = res.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Ground ground = objectMapper.readValue(content, Ground.class);

        repository.deleteById(ground.getId());

    }

    public String getObject(){
        return "{\"propName\":\"Myname\",\"district\":{\"propDistrict\":9,\"valueDistrictM2\":10},\"rooms\":[{\"roomName\":\"Qualquercoisa\",\"roomLength\":10,\"roomWidth\":2},{\"roomName\":\"Qualquer\",\"roomLength\":10,\"roomWidth\":1},{\"roomName\":\"Outracoisa\",\"roomLength\":10,\"roomWidth\":1}]}";
    }

}
