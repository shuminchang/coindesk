package com.cathay.coindesk.controller;

import com.cathay.coindesk.model.Currency;
import com.cathay.coindesk.respository.CurrencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        currencyRepository.deleteAll();
        currencyRepository.save(new Currency("USD", "美元"));
    }

    @Test
    public void testGetAllCurrencies() throws Exception {
        mockMvc.perform(get("/api/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].code", is("USD")))
                .andExpect(jsonPath("$[0].name", is("美元")));
    }

    @Test
    public void testCreateCurrency() throws Exception {
        Currency currency = new Currency("JPY", "日圓");
        mockMvc.perform(post("/api/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currency)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("JPY")))
                .andExpect(jsonPath("$.name", is("日圓")));
    }

    @Test
    public void testUpdateCurrency() throws Exception {
        Currency updated = new Currency("USD", "美金");
        mockMvc.perform(put("/api/currencies/USD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("美金")));
    }

    @Test
    public void testDeleteCurrency() throws Exception {
        mockMvc.perform(delete("/api/currencies/USD"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/currencies/USD"))
                .andExpect(status().isOk())
                .andExpect(content().string("null"));  // <-- 修正這一行
    }
}