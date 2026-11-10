package org.ais.hr.fi.Repo;

import org.ais.hr.fi.model.LkpCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LkpCityRepo extends JpaRepository<LkpCity,Long> {

    List<LkpCity> findByLkpCountryCountryId(Long countryId);
}
