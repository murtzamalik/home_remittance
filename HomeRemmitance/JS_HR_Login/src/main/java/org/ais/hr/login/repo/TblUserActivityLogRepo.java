package org.ais.hr.login.repo;

import org.ais.hr.login.model.TblUserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserActivityLogRepo extends JpaRepository<TblUserActivityLog,Integer> {

}
