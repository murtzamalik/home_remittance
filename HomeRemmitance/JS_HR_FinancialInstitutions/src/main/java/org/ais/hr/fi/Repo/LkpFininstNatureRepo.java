package org.ais.hr.fi.Repo;

import org.ais.hr.fi.model.LkpFininstNature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpFininstNatureRepo extends JpaRepository<LkpFininstNature,Long> {

    LkpFininstNature findByCode(String code);
}
