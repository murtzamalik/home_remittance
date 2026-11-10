package org.ais.hr.user_management.Repo;




import org.ais.hr.user_management.model.TblUserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserActivityLogRepo extends JpaRepository<TblUserActivityLog,Long> {

}
