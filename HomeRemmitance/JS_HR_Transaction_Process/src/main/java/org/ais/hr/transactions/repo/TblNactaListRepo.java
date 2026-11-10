package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblNactaList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblNactaListRepo extends JpaRepository<TblNactaList,Long> {

    List<TblNactaList> findByProcessName(String processName);

    @Modifying
    @Query(value = "DELETE FROM TBL_NACTA_LIST WHERE PROCESS_NAME = :processName AND PROCESS_TYPE = :processType ",nativeQuery = true)
    int deleteAllPreviousRecordAgainstProcessNameAndType(@Param("processName") String processName,@Param("processType") String processType);
}
