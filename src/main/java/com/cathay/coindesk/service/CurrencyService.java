package com.cathay.coindesk.service;

import com.cathay.coindesk.model.CoindeskRecord;
import com.cathay.coindesk.model.Currency;
import com.cathay.coindesk.respository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAllRecords() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getRecordByCode(String code) {
        return currencyRepository.findByCode(code);
    }

    public Currency createRecord(Currency record) {
        return currencyRepository.save(record);
    }

    public Currency updateRecord(String code, Currency updated) {
        return currencyRepository.findByCode(code).map(record -> {
            record.setCode(updated.getCode());
            record.setName(updated.getName());
            return currencyRepository.save(record);
        }).orElseThrow(() -> new RuntimeException("Record not found: " + code));
    }

    @Transactional
    public void deleteRecord(String code) {
        currencyRepository.deleteByCode(code);
    }
}
