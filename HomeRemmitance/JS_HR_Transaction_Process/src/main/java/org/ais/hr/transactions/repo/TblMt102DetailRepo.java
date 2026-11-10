package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblMt102Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ais.hr.transactions.model.TblMt102Head;
import java.util.List;

public interface TblMt102DetailRepo extends JpaRepository<TblMt102Detail,Long> {


    List<TblMt102Detail> findByTblMt102HeadMt102HeadId(long tblMt102HeadId);



}
