package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.LkpStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpStatusRepo extends JpaRepository<LkpStatus,Long> {
}
