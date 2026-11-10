package org.ais.hr.user_management.Repo;

import org.ais.hr.user_management.model.TblMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblMenuRepo extends JpaRepository<TblMenu , Long> {


    @Query(value = "select * from TBL_MENU a where a.MODULE_ID = :moduleId ", nativeQuery = true)
    List<TblMenu> getMenuByModuleId(@Param("moduleId") Long moduleId);

}
