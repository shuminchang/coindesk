package com.cathay.coindesk.service;

import com.cathay.coindesk.model.CoindeskRecord;
import com.cathay.coindesk.model.CoindeskTransformedDto;
import com.cathay.coindesk.respository.CoindeskRecordRepository;
import com.cathay.coindesk.respository.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cathay.coindesk.model.Currency;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoindeskService {

    @Autowired
    private CurrencyRepository currencyRepo;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchOriginalJson() {
        return restTemplate.getForObject("https://kengp3.github.io/blog/coindesk.json", String.class);
    }

    public CoindeskTransformedDto getTransformedData() throws JsonProcessingException {
        String json = fetchOriginalJson();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        String isoTime = root.path("time").path("updatedISO").asText();
        String formattedTime = ZonedDateTime.parse(isoTime)
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        JsonNode bpi = root.path("bpi");

        List<CoindeskTransformedDto.CurrencyInfo> list = new ArrayList<>();
        bpi.fieldNames().forEachRemaining(code -> {
            JsonNode item = bpi.get(code);
            BigDecimal rate = item.path("rate_float").decimalValue();
            String name = currencyRepo.findById(code)
                    .map(Currency::getName).orElse("未知");

            CoindeskTransformedDto.CurrencyInfo info = new CoindeskTransformedDto.CurrencyInfo();
            info.setCode(code);
            info.setName(name);
            info.setRateFloat(rate);
            list.add(info);
        });

        CoindeskTransformedDto result = new CoindeskTransformedDto();
        result.setUpdateTime(formattedTime);
        result.setCurrencyList(list);
        return result;
    }
}
