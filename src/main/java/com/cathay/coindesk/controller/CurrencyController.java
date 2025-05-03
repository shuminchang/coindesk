package com.cathay.coindesk.controller;

import com.cathay.coindesk.model.Currency;
import com.cathay.coindesk.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAllRecords();
    }

    @PostMapping
    public Currency create(@RequestBody Currency c) {
        return currencyService.createRecord(c);
    }

    @GetMapping("/{code}")
    public Optional<Currency> getCurrencyById(@PathVariable String code) {
        return currencyService.getRecordByCode(code);
    }

    @PutMapping("/{code}")
    public Currency update(@PathVariable String code, @RequestBody Currency updated) {
        Optional<Currency> c = currencyService.getRecordByCode(code);
        if (c.isPresent()) {
            Currency currency = c.get();
            currency.setName(updated.getName());
            return currencyService.updateRecord(code, currency);
        } else {
            throw new RuntimeException("Currency not found: " + code);
        }
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        currencyService.deleteRecord(code);
    }
}