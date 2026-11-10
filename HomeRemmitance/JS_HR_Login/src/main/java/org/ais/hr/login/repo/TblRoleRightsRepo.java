package org.ais.hr.login.repo;

import org.ais.hr.login.model.TblRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblRoleRightsRepo extends JpaRepository<TblRoleRight,Long> {
    @Query(value = "select DISTINCT rr.*\n" +
            "            from TBL_MENU m,TBL_MODULE md,tbl_role_rights rr\n" +
            "            where m.module_id = md.module_id\n" +
            "            and rr.menu_id = m.menu_id\n" +
            "            and md.module_id = :moduleCode \n" +
            "            and rr.role_id = :roleCode ", nativeQuery = true)
    List<TblRoleRight> getRoleAndModuelWisePages(@Param("moduleCode") Long moduleCode, @Param("roleCode") Long roleCode);
}
