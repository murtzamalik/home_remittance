package org.ais.hr.login.repo;

import org.ais.hr.login.model.TblAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface TblAuthAccessTokenRepo extends JpaRepository<TblAuthAccessToken,Long> {
    TblAuthAccessToken findByUserIdAndAccessToken(BigDecimal userId,String token);

    @Transactional
    void deleteByUserIdAndAccessToken(BigDecimal userId, String token);

    @Transactional
    void deleteByUserId(BigDecimal userId);
}
