package com.cathay.coindesk.respository;

import com.cathay.coindesk.model.CoindeskRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoindeskRecordRepository extends JpaRepository<CoindeskRecord, Long> {
}
