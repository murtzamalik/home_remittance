package org.ais.hr.transactions.repo;



import org.ais.hr.transactions.model.TblUserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserActivityLogRepo extends JpaRepository<TblUserActivityLog,Long> {

}
