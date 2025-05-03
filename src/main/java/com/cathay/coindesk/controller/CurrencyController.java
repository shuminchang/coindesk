package com.cathay.coindesk.controller;

import com.cathay.coindesk.model.CoindeskRecord;
import com.cathay.coindesk.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public List<CoindeskRecord> getAllRecords() {
        return currencyService.getAllRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoindeskRecord> getRecordById(@PathVariable Long id) {
        return currencyService.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public CoindeskRecord createRecord(@RequestBody CoindeskRecord record) {
        return currencyService.createRecord(record);
    }

    @PutMapping("/{id}")
    public CoindeskRecord updateRecord(@PathVariable Long id, @RequestBody CoindeskRecord updatedCoindeskRecord) {
        return currencyService.updateRecord(id, updatedCoindeskRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        currencyService.deleteRecord(id);
    }
}
