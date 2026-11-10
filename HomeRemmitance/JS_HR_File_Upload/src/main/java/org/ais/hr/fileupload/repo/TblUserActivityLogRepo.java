package org.ais.hr.fileupload.repo;

import org.ais.hr.fileupload.model.TblUserActivityLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserActivityLogRepo extends JpaRepository<TblUserActivityLog,Integer> {

}
