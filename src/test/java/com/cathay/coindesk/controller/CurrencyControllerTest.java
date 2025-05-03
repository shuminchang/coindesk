package com.cathay.coindesk.controller;

import com.cathay.coindesk.model.CoindeskRecord;
import com.cathay.coindesk.respository.CoindeskRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoindeskRecordRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private CoindeskRecord sampleRecord;

    @BeforeEach
    public void setup() {
        repository.deleteAll();

        sampleRecord = new CoindeskRecord();
        sampleRecord.setCode("USD");
        sampleRecord.setSymbol("$");
        sampleRecord.setRate("57,000.00");
        sampleRecord.setRateFloat(new BigDecimal("57000.00"));
        sampleRecord.setDescription("United States Dollar");
        sampleRecord.setUpdateTime("2024/09/02 12:00:00");
        repository.save(sampleRecord);
    }

    @Test
    public void testGetAllRecords() throws Exception {
        mockMvc.perform(get("/api/currency/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code").value("USD"));
    }

    @Test
    public void testGetRecordById() throws Exception {
        Long id = sampleRecord.getId();

        mockMvc.perform(get("/api/currency/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("USD"));
    }

    @Test
    public void testCreateRecord() throws Exception {
        CoindeskRecord newRecord = new CoindeskRecord();
        newRecord.setCode("EUR");
        newRecord.setSymbol("€");
        newRecord.setRate("52000.00");
        newRecord.setRateFloat(new BigDecimal("52000.00"));
        newRecord.setDescription("Euro");
        newRecord.setUpdateTime("2024/09/02 13:00:00");

        mockMvc.perform(post("/api/currency/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("EUR"));
    }

    @Test
    public void testUpdateRecord() throws Exception {
        Long id = sampleRecord.getId();
        sampleRecord.setDescription("Updated USD");

        mockMvc.perform(put("/api/currency/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated USD"));
    }

    @Test
    public void testDeleteRecord() throws Exception {
        Long id = sampleRecord.getId();

        mockMvc.perform(delete("/api/currency/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/currency/{id}", id))
                .andExpect(status().isNotFound()); // <--- 修正這行

    }
}