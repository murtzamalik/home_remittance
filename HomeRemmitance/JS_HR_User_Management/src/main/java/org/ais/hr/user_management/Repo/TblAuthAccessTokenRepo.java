package org.ais.hr.user_management.Repo;



import org.ais.hr.user_management.model.TblAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TblAuthAccessTokenRepo extends JpaRepository<TblAuthAccessToken,Long> {
    TblAuthAccessToken findByUserIdAndAccessToken(BigDecimal userId, String token);
}
