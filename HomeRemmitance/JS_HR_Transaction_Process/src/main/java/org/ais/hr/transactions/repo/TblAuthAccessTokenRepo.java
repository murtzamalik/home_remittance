package org.ais.hr.transactions.repo;


import org.ais.hr.transactions.model.TblAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TblAuthAccessTokenRepo extends JpaRepository<TblAuthAccessToken,Long> {
    TblAuthAccessToken findByUserIdAndAccessToken(BigDecimal userId, String token);
}
