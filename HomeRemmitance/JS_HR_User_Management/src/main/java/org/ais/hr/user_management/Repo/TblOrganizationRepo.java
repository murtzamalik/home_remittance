package org.ais.hr.user_management.Repo;

import org.ais.hr.user_management.model.TblOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblOrganizationRepo extends JpaRepository<TblOrganization,Long> {
    TblOrganization findByOrganizationCode(String organizationCode);
}
