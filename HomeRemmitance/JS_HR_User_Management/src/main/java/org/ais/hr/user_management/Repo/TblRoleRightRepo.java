package org.ais.hr.user_management.Repo;

import org.ais.hr.user_management.model.TblRoleRight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblRoleRightRepo extends JpaRepository<TblRoleRight , Long> {

    List<TblRoleRight> findByTblRoleRoleId(Long roleId);

    @Query(value = "select DISTINCT rr.*\n" +
            "            from TBL_MENU m,TBL_MODULE md,tbl_role_rights rr\n" +
            "            where m.module_id = md.module_id\n" +
            "            and rr.menu_id = m.menu_id\n" +
            "            and md.module_id = :moduleCode \n" +
            "            and rr.role_id = :roleCode ", nativeQuery = true)
    List<TblRoleRight> getRoleAndModuelWisePages(@Param("moduleCode") Long moduleCode, @Param("roleCode") Long roleCode);
}
