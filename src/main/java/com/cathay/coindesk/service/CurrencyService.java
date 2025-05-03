package com.cathay.coindesk.service;

import com.cathay.coindesk.model.CoindeskRecord;
import com.cathay.coindesk.respository.CoindeskRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CoindeskRecordRepository coindeskRecordRepository;

    public List<CoindeskRecord> getAllRecords() {
        return coindeskRecordRepository.findAll();
    }

    public Optional<CoindeskRecord> getRecordById(Long id) {
        return coindeskRecordRepository.findById(id);
    }

    public CoindeskRecord createRecord(CoindeskRecord record) {
        return coindeskRecordRepository.save(record);
    }

    public CoindeskRecord updateRecord(Long id, CoindeskRecord updated) {
        return coindeskRecordRepository.findById(id).map(record -> {
            record.setCode(updated.getCode());
            record.setSymbol(updated.getSymbol());
            record.setRate(updated.getRate());
            record.setRateFloat(updated.getRateFloat());
            record.setDescription(updated.getDescription());
            record.setUpdateTime(updated.getUpdateTime());
            return coindeskRecordRepository.save(record);
        }).orElseThrow(() -> new RuntimeException("Record not found: " + id));
    }

    public void deleteRecord(Long id) {
        coindeskRecordRepository.deleteById(id);
    }
}
