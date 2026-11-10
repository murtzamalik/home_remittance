package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblMt102Detail;
import org.ais.hr.transactions.model.TblMt102Temp;
import org.ais.hr.transactions.model.TblTransDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface TblMt102TempRepo extends JpaRepository<TblMt102Temp,Long> {

    @Query(value = "select LKP_CITY_SEQ.nextval from dual",nativeQuery = true)
    BigDecimal getBatchNumberForBT();

}
