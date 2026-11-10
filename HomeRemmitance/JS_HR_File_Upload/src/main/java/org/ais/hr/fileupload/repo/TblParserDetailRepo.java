package org.ais.hr.fileupload.repo;

import org.ais.hr.fileupload.model.TblParserDetail;
import org.ais.hr.fileupload.model.TblTransDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TblParserDetailRepo extends JpaRepository<TblParserDetail , Long> {

    List<TblParserDetail> findByTblParserHeadParserHeadIdOrderBySequenceAsc(long parserHeadId);

    @Query(value = "SELECT D.* \n" +
            "FROM TBL_PARSER_HEAD H\n" +
            "INNER JOIN TBL_PARSER_DETAIL D ON H.PARSER_HEAD_ID = D.PARSER_HEAD_ID\n" +
            "INNER JOIN TBL_TRANS_FIELDS F ON D.TRANS_FIELDS_ID = F.TRANS_FIELDS_ID\n" +
            "WHERE H.PARSER_HEAD_ID = :parserHeadId AND F.COLUMN_NAME = :columnName ",nativeQuery = true)
    TblParserDetail getSeqNumFromColumnName(@Param("parserHeadId") String parserHeadId,@Param("columnName") String columnName);

}
