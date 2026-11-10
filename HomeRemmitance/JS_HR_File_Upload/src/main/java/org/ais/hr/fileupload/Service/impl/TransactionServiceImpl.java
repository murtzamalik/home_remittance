package org.ais.hr.fileupload.Service.impl;

import org.ais.hr.fileupload.Service.TransactionService;
import org.ais.hr.fileupload.model.*;
import org.ais.hr.fileupload.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/27/2022
 * Time: 5:02 PM
 * Project : login
 */



@Service
@Transactional(rollbackOn = Exception.class)
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TblTransDetailRepo tblTransDetailRepo;

    @Autowired
    private TblParserDetailRepo tblParserDetailRepo;

    @Autowired
    private TblFininstBankRepo tblFininstBankRepo;

    @Autowired
    private TblAuthAccessTokenRepo authAccessTokenRepo;

    @Autowired
    private TblUserActivityLogRepo tblUserActivityLogRepo;


    @Override
    public List<TblTransDetail> getAllAmendmentsTransactions() {
        return tblTransDetailRepo.findByTransAmd("Y");
    }

    @Override
    public TblAuthAccessToken tokenVerification(BigDecimal userId, String header) {
        return authAccessTokenRepo.findByUserIdAndAccessToken(userId,header);
    }

    @Override
    public TblUserActivityLog acticityLogging(TblUserActivityLog tblUserActivityLog) {
        return tblUserActivityLogRepo.saveAndFlush(tblUserActivityLog);
    }
}
