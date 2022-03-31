package com.mercadolivre.desafio_quality.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafio_quality.model.Ground;
import com.mercadolivre.desafio_quality.repository.GroundRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroundRepository repository;

    private void creatGround() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ground")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObject()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    public void shouldCreateAGroundAndReturn201() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ground")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObject()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Ground ground = objectMapper.readValue(response, Ground.class);
        repository.deleteById(ground.getId());
    }

    public String getObject(){
        return "{\"propName\":\"Myname\",\"district\":{\"propDistrict\":9,\"valueDistrictM2\":10},\"rooms\":[{\"roomName\":\"Qualquercoisa\",\"roomLength\":10,\"roomWidth\":2},{\"roomName\":\"Qualquer\",\"roomLength\":10,\"roomWidth\":1},{\"roomName\":\"Outracoisa\",\"roomLength\":10,\"roomWidth\":1}]}";
    }

    @Test
    public void shouldGroundValueReturn200() throws Exception {
        creatGround();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ground/{groundID}/value", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("400,00", response);
    }

    @Test
    public void shouldGroundAreaReturn200() throws Exception {
        creatGround();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ground/{groundID}/area", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("40.0", response);
    }

    @Test
    public void shouldBiggestRoomAreaReturn200() throws Exception {
        creatGround();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ground/{groundID}/room/biggest", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertEquals("{\"name\":\"Qualquercoisa\",\"area\":20.0}", response);
    }

}
