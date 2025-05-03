package com.cathay.coindesk.respository;

import com.cathay.coindesk.model.CoindeskRecord;
import com.cathay.coindesk.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    Optional<Currency> findByCode(String code);

    void deleteByCode(String code);
}
