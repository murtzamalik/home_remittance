package org.ais.hr.fi.Service;

import org.ais.hr.fi.dto.*;
import org.ais.hr.fi.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface FinInstService {

    TblFininstBank saveFinInst(TblFininstBank tblFininstBank);

    TblFininstBank updateFinInst(UpdateFinIstBankRequest updateFinIstBankRequest, LoggedUserDetail loggedUserDetail);

    List<TblFininstBank> getAllFininst(String nature);

    TblFininstBank updateFinInst(UpdateFininstBank updateFininstBank , LoggedUserDetail loggedUserDetail);

    TblParserHead saveParserHead(ParserHeadRequest parserHeadRequest, LoggedUserDetail loggedUserDetail);

    TblParserDetail saveParserDetails(ParserDetailRequest parserDetailRequest, LoggedUserDetail loggedUserDetail);

    TblFininstBank getSingleFinInsts(String fininstBankId);

    List<TblParserHead> getAllParser();

    TblParserHead updateParserHead(ParserHeadUpdateRequest parserHeadUpdateRequest, LoggedUserDetail loggedUserDetail);

    TblParserDetail updateParserDetails(ParserDetailUpdateRequest parserDetailRequest, LoggedUserDetail loggedUserDetail);

    TblRemitMode saveRemitMode(TblRemitMode tblRemitMode);

    TblRemitMode updateRemitMode(UpdateRemitModeRequest updateRemitModeRequest, LoggedUserDetail loggedUserDetail);

    List<TblRemitMode> getAllRemittMode();

    List<TblParserDetail> findByTblParserHeadId(long parserHeadId);

    TblAuthAccessToken tokenVerification(BigDecimal userId, String header);

    TblUserActivityLog acticityLogging(TblUserActivityLog tblUserActivityLog);
}
  