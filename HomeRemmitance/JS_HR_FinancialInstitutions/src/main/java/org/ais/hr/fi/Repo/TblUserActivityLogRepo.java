package org.ais.hr.fi.Repo;


import org.ais.hr.fi.model.TblUserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserActivityLogRepo extends JpaRepository<TblUserActivityLog,Long> {

}
