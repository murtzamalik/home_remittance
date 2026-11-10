package org.ais.hr.fi.Repo;

import org.ais.hr.fi.model.TblAuthAccessToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TblAuthAccessTokenRepo extends JpaRepository<TblAuthAccessToken,Long> {
    TblAuthAccessToken findByUserIdAndAccessToken(BigDecimal userId, String token);
}
