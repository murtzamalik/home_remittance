package org.ais.hr.fileupload.repo;

import org.ais.hr.fileupload.model.TblGlobalConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TblGlobalConfigRepo extends JpaRepository<TblGlobalConfig, BigDecimal> {

    TblGlobalConfig findByKeyName(String keyName);
}
