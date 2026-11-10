package org.ais.hr.fi.Repo;

import org.ais.hr.fi.model.TblParserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblParserDetailRepo extends JpaRepository<TblParserDetail,Long> {
    List<TblParserDetail> findByTblParserHeadParserHeadIdOrderBySequenceAsc(long parserHeadId);
}
