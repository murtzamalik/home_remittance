package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblGlobalConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TblGlobalConfigRepo extends JpaRepository<TblGlobalConfig, BigDecimal> {
    TblGlobalConfig findByKeyName(String keyName);
}
