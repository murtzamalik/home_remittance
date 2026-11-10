package org.ais.hr.login.repo;

import org.ais.hr.login.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblUserRepo extends JpaRepository<TblUser,Long> {


    TblUser findByLdapEmpcode(String employeeNo);
}
