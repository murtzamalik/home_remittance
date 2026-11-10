package org.ais.hr.fileupload.repo;

import org.ais.hr.fileupload.model.TblAuthAccessToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TblAuthAccessTokenRepo extends JpaRepository<TblAuthAccessToken,Long> {
    TblAuthAccessToken findByUserIdAndAccessToken(BigDecimal userId, String token);
}
