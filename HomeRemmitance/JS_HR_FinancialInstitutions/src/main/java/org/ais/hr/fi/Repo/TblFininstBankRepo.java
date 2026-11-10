package org.ais.hr.fi.Repo;

import org.ais.hr.fi.model.TblFininstBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblFininstBankRepo extends JpaRepository<TblFininstBank,Long> {

    List<TblFininstBank>  findByFininstNature(String nature);
}
