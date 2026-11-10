package org.ais.hr.login.repo;

import org.ais.hr.login.model.TblUserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblUserLoginHistoryRepo extends JpaRepository<TblUserLoginHistory,Long> {
}
