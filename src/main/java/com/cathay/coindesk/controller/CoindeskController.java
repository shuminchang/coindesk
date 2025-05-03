package com.cathay.coindesk.controller;

import com.cathay.coindesk.model.CoindeskTransformedDto;
import com.cathay.coindesk.service.CoindeskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @GetMapping("/original")
    public String getOriginalData() {
        return coindeskService.fetchOriginalJson();
    }

    @GetMapping("/transformed-and-save")
    public CoindeskTransformedDto getAndSave() throws JsonProcessingException {
        return coindeskService.getTransformedDataAndSave();
    }
}
