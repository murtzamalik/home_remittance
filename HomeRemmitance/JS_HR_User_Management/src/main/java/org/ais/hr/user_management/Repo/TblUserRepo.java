package org.ais.hr.user_management.Repo;

import org.ais.hr.user_management.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TblUserRepo extends JpaRepository<TblUser,Long> {

    @Query(value = "select u.USER_ID, u.USERCODE, u.USERNAME, u.PASSWORD, u.EMP_CODE, u.EMPLOYEE_NO, u.KEY_VALUE, u.TIMEZONE, u.SIGNATURE, u.IS_ACTIVE, u.TIME_CHECK, u.IDLE_TIME, u.IDLE_TIMEACTION, u.NO_ATTEMPT, u.ATTEMPT_PERIOD, u.ATTEMPT_ACTION, u.ATTEMPTED, u.LAST_LOGIN, u.START_DATE, u.END_DATE, u.REMARKS,\n" +
            "            u.ALLOW_CONCON,u.CON_CONNECTION,u.PASS_EXP_DAYS,u.VERIFY_OWN_TRANS,u.INACTIVE_DAYS,u.APPROVE_LMTTRAN,u.LAST_PASS_CHANGE,u.ORGANIZATION_ID,u.LOCATION_ID,u.LEVEL_CODE,u.LOG_EVENT_LISTER,u.LOG_EVENT_MENU,u.LOG_EVENT_BUTTON,u.LOG_EVENT_OPTION\n" +
            "            ,u.PASSWORD_EXPIRED,u.PASS_EXP_DATE,u.PASS_GRACE_PERIOD,u.STATUS_CODE,u.CAN_VERIFY,u.CAN_APPROVE,u.APPROVE_OWN_TRANS\n" +
            "            ,u.APPROVE_VER_TRANS,u.CREATEUSER,u.CREATEDATE,u.LASTUPDATEUSER,u.LASTUPDATEDATE,u.UPDATEINDEX,u.INSTRUCTION_OF,ACTIVATION_REASON_ID,u.INACTIVATION_REASON_ID,u.ACTIVATION_COMMENT,u.INACTIVATION_COMMENT,u.EMAIL,REASON,PER_INACTIVATION\n" +
            "            ,u.MOBILENO,HTYPE,u.INTERNAL_EXTERNAL,u.LDAP_NAME,LDAP_EMAIL,u.LDAP_EMPCODE,u.ROLE_ID,r.ROLE_DESCR,l.LOCATION_DESCR,l.LOCATION_CODE \n" +
            "from TBL_USER u \n" +
            "left outer join Tbl_Location l on u.location_id = l.location_id \n" +
            "left outer join Tbl_Role r on u.role_id=r.role_id", nativeQuery = true)
    List<Object> getAllUsers();

}
