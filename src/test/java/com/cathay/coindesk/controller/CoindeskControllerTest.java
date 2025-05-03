package com.cathay.coindesk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CoindeskControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFetchOriginalCoindeskAPI() {
        String url = "/api/coindesk/original";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println("原始資料：\n" + response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("bpi"));
    }

    @Test
    public void testFetchTransformedCoindeskAPI() {
        String url = "/api/coindesk/transformed-and-save";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println("轉換後資料：\n" + response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("updateTime"));
        assertTrue(response.getBody().contains("currencyList"));
    }
}
