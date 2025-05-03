package com.cathay.coindesk.service;

import com.cathay.coindesk.model.CoindeskTransformedDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoindeskService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchOriginalJson() {
        return restTemplate.getForObject("https://kengp3.github.io/blog/coindesk.json", String.class);
    }

    public CoindeskTransformedDto getTransformedData() throws JsonProcessingException {
        String json = fetchOriginalJson();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        String isoTime = root.path("time").path("updatedISO").asText();
        String formattedTime = ZonedDateTime.parse(isoTime).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        JsonNode bpi = root.path("bpi");

        List<CoindeskTransformedDto.CurrencyInfo> currencyList = new ArrayList<>();
        bpi.fieldNames().forEachRemaining(item -> {
            JsonNode currency = bpi.get(item);
            String code = String.valueOf(currency.path("code"));
            String symbol = String.valueOf(currency.path("symbol"));
            String rate = String.valueOf(currency.path("rate"));
            String description = String.valueOf(currency.path("description"));
            BigDecimal rateFloat = currency.path("rate_float").decimalValue();

            CoindeskTransformedDto.CurrencyInfo info = new CoindeskTransformedDto.CurrencyInfo();
            info.setCode(code);
            info.setSymbol(symbol);
            info.setRate(rate);
            info.setDescription(description);
            info.setRateFloat(rateFloat);
            currencyList.add(info);
        });

        CoindeskTransformedDto result = new CoindeskTransformedDto();
        result.setUpdateTime(formattedTime);
        result.setCurrencyList(currencyList);

        return result;
    }
}
