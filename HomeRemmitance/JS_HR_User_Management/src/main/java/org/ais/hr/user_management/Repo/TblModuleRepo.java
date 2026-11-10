package org.ais.hr.user_management.Repo;

import org.ais.hr.user_management.model.TblModule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblModuleRepo extends JpaRepository<TblModule , Long> {

    @Query(value = "select DISTINCT md.*\n" +
            "from TBL_MENU m,TBL_MODULE md,tbl_role_rights rr\n" +
            "where m.module_id = md.module_id\n" +
            "and rr.menu_id = m.menu_id\n" +
            "and rr.role_id = :roleCode ", nativeQuery = true)
    List<TblModule> getRoleWiseModule(@Param("roleCode") Long roleCodes);

}
