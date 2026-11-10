package org.ais.hr.fi.Service.impl;

import org.ais.hr.fi.Repo.*;
import org.ais.hr.fi.Service.FinInstService;
import org.ais.hr.fi.dto.*;
import org.ais.hr.fi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class FinInstServiceImpl implements FinInstService {

    @Autowired
    private TblFininstBankRepo tblFininstBankRepo;
    @Autowired
    private TblFininstGlRepo tblFininstGlRepo;
    @Autowired
    private TblGlAccountRepo tblGlAccountRepo;
    @Autowired
    private TblTransFieldRepo tblTransFieldRepo;
    @Autowired
    private TblParserHeadRepo tblParserHeadRepo;
    @Autowired
    private TblParserDetailRepo tblParserDetailRepo;
    @Autowired
    private LkpFininstNatureRepo lkpFininstNatureRepo;
    @Autowired
    private TblRemitModeRepo tblRemitModeRepo;
    @Autowired
    private TblAuthAccessTokenRepo authAccessTokenRepo;
    @Autowired
    private TblUserActivityLogRepo tblUserActivityLogRepo;


    @Override
    public TblFininstBank saveFinInst(TblFininstBank tblFininstBank) {

        tblFininstBank = tblFininstBankRepo.saveAndFlush(tblFininstBank);
        if (tblFininstBank != null && tblFininstBank.getFininstBankId() > 0) {

            tblFininstBank.setTblGlAccount(tblGlAccountRepo.saveAndFlush(tblFininstBank.getTblGlAccount()));


            TblFininstGl tblFininstGl = new TblFininstGl();
            tblFininstGl.setIsActive("Y");
            tblFininstGl.setTblFininstBank(tblFininstBank);
            tblFininstGl.setTblGlAccount(tblFininstBank.getTblGlAccount());
            tblFininstGl.setCreateuser(tblFininstBank.getCreateuser());

            tblFininstGlRepo.saveAndFlush(tblFininstGl);

            return tblFininstBank;
        } else {
            return null;
        }
    }

    @Override
    public List<TblFininstBank> getAllFininst(String nature) {
        return tblFininstBankRepo.findByFininstNature(nature);
    }

    @Override
    public TblFininstBank updateFinInst(UpdateFininstBank updateFininstBank, LoggedUserDetail loggedUserDetail) {

        TblFininstBank tblFininstBank = tblFininstBankRepo.findById(loggedUserDetail.getLoginId()).orElse(null);

        if (tblFininstBank != null) {
            tblFininstBank.setActiveTag(updateFininstBank.getIsAtcive());

            tblFininstBank = tblFininstBankRepo.saveAndFlush(tblFininstBank);
            return tblFininstBank;
        } else {
            return null;
        }


    }

    @Override
    public TblParserHead saveParserHead(ParserHeadRequest parserHeadRequest, LoggedUserDetail loggedUserDetail) {
        TblParserHead tblParserHead = new TblParserHead();

        tblParserHead.setCode(parserHeadRequest.getCode());
        tblParserHead.setDescription(parserHeadRequest.getDescription());
        tblParserHead.setIsActive(parserHeadRequest.getIsActive());
        tblParserHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

        return tblParserHeadRepo.saveAndFlush(tblParserHead);
    }

    @Override
    public TblParserDetail saveParserDetails(ParserDetailRequest parserDetailRequest, LoggedUserDetail loggedUserDetail) {

        TblParserDetail tblParserDetail = new TblParserDetail();

        TblParserHead tblParserHead = new TblParserHead();
        TblTransField tblTransField = new TblTransField();
        tblParserHead.setParserHeadId(parserDetailRequest.getParserHeadId());
        tblTransField.setTransFieldsId(parserDetailRequest.getTransFieldId());
        tblParserDetail.setTblParserHead(tblParserHead);
        tblParserDetail.setTblTransField(tblTransField);
        tblParserDetail.setFixedLength(parserDetailRequest.getFixedLength());
        tblParserDetail.setIsMandatory(parserDetailRequest.getIsMandatory());
        tblParserDetail.setMaxLength(parserDetailRequest.getMaxLength());
        tblParserDetail.setMinLength(parserDetailRequest.getMinLength());
        tblParserDetail.setRefFieldName(parserDetailRequest.getRefFieldName());
        tblParserDetail.setSequence(BigDecimal.valueOf(parserDetailRequest.getSequence()));
        tblParserDetail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

        return tblParserDetailRepo.saveAndFlush(tblParserDetail);
    }

    @Override
    public TblFininstBank updateFinInst(UpdateFinIstBankRequest updateFinIstBankRequest, LoggedUserDetail loggedUserDetail) {

        TblFininstBank tblFininstBank = tblFininstBankRepo.findById(updateFinIstBankRequest.getFininstBankId()).orElse(null);
        if (tblFininstBank != null) {



            LkpCity lkpCity = new LkpCity();
            LkpCountry lkpCountry1 = new LkpCountry();
            TblGlAccount tblGlAccount = new TblGlAccount();
            LkpGlAccountType lkpGlAccountType = new LkpGlAccountType();
            TblParserHead tblParserHead = new TblParserHead();

            lkpCity.setCityId(updateFinIstBankRequest.getLkpCityId());
            lkpCountry1.setCountryId(updateFinIstBankRequest.getLkpCountry1Id());
            lkpGlAccountType.setGlAccountTypeId(updateFinIstBankRequest.getGlLkpGlAccountTypeId());
            tblParserHead.setParserHeadId(updateFinIstBankRequest.getParserHeadId());



            tblFininstBank.setCcEmail(updateFinIstBankRequest.getCcEmail());
            tblFininstBank.setContactPerson(updateFinIstBankRequest.getContactPerson());
            tblFininstBank.setEmail(updateFinIstBankRequest.getEmail());
            tblFininstBank.setFax(updateFinIstBankRequest.getFax());
            tblFininstBank.setFininstBankcode(updateFinIstBankRequest.getFininstBankcode());
            tblFininstBank.setFininstCode(updateFinIstBankRequest.getFininstCode());
            tblFininstBank.setFininstDefault(updateFinIstBankRequest.getFininstDefault());
            tblFininstBank.setFininstDescr(updateFinIstBankRequest.getFininstDescr());
            tblFininstBank.setFininstNature(updateFinIstBankRequest.getFininstNature());
            tblFininstBank.setFininstSbpcode(updateFinIstBankRequest.getFininstSbpcode());
            tblFininstBank.setFininstShort(updateFinIstBankRequest.getFininstShort());
            tblFininstBank.setIbftAccountFormat(updateFinIstBankRequest.getIbftAccountFormat());
            tblFininstBank.setIbftAccountLength(updateFinIstBankRequest.getIbftAccountLength());
            tblFininstBank.setIsActive(updateFinIstBankRequest.getIsActive());
            tblFininstBank.setMobile(updateFinIstBankRequest.getMobile());
            tblFininstBank.setPhone(updateFinIstBankRequest.getPhone());
            tblFininstBank.setPobox(updateFinIstBankRequest.getPobox());
            tblFininstBank.setPostal(updateFinIstBankRequest.getPostal());
            tblFininstBank.setRegAddress1(updateFinIstBankRequest.getRegAddress1());
            tblFininstBank.setRegAddress2(updateFinIstBankRequest.getRegAddress2());
            tblFininstBank.setRegAddress3(updateFinIstBankRequest.getRegAddress3());
            tblFininstBank.setValidateIban(updateFinIstBankRequest.getValidateIban());
            tblFininstBank.setWebsite(updateFinIstBankRequest.getWebsite());


            tblGlAccount.setFcAllowed(updateFinIstBankRequest.getGlFcAllowed());
            tblGlAccount.setGlAccountCode(updateFinIstBankRequest.getGlAccountCode());
            tblGlAccount.setGlAccountDescr(updateFinIstBankRequest.getGlAccountDescr());
            tblGlAccount.setGlAccountShort(updateFinIstBankRequest.getGlAccountShort());
            tblGlAccount.setIsActive(updateFinIstBankRequest.getIsActive());

            tblGlAccount.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

            tblGlAccount.setLkpGlAccountType(lkpGlAccountType);




            tblFininstBank.setTblGlAccount(tblGlAccount);
            tblFininstBank.setLkpCountry1(lkpCountry1);
            tblFininstBank.setLkpCountry2(null);
            tblFininstBank.setParserHeadId(tblParserHead);

            tblFininstBank.setLastupdatedate(new Date());
            tblFininstBank.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblFininstBank.setUpdateindex(tblFininstBank.getUpdateindex() != null ? new BigDecimal(tblFininstBank.getUpdateindex().longValue() + 1) : new BigDecimal(1));




            return tblFininstBankRepo.saveAndFlush(tblFininstBank);


        } else {
            return null;
        }
    }

    @Override
    public TblFininstBank getSingleFinInsts(String fininstBankId) {
        TblFininstBank fininstBank = tblFininstBankRepo.findById(Long.valueOf(fininstBankId)).orElse(null);
        if (fininstBank != null) {
            LkpFininstNature lkpFininstNature = lkpFininstNatureRepo.findByCode(fininstBank.getFininstNature());
            fininstBank.setLkpFininstNature(lkpFininstNature);

            return fininstBank;
        } else {
            return null;
        }
    }

    @Override
    public List<TblParserHead> getAllParser() {
        return tblParserHeadRepo.findAll();
    }

    @Override
    public TblParserHead updateParserHead(ParserHeadUpdateRequest parserHeadUpdateRequest, LoggedUserDetail loggedUserDetail) {
        TblParserHead tblParserHead = tblParserHeadRepo.findById(parserHeadUpdateRequest.getParserHeadId()).orElse(null);

        tblParserHead.setCode(parserHeadUpdateRequest.getCode());
        tblParserHead.setDescription(parserHeadUpdateRequest.getDescription());
        tblParserHead.setIsActive(parserHeadUpdateRequest.getIsActive());
        tblParserHead.setLastupdatedate(new Date());
        tblParserHead.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblParserHead.setUpdateindex(tblParserHead.getUpdateindex() != null ? new BigDecimal(tblParserHead.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblParserHeadRepo.saveAndFlush(tblParserHead);

    }

    @Override
    public TblParserDetail updateParserDetails(ParserDetailUpdateRequest parserDetailRequest, LoggedUserDetail loggedUserDetail) {
        TblParserDetail tblParserDetail = tblParserDetailRepo.findById(parserDetailRequest.getParserDetailId()).orElse(null);


        tblParserDetail.setFixedLength(parserDetailRequest.getFixedLength());
        tblParserDetail.setIsMandatory(parserDetailRequest.getIsMandatory());
        tblParserDetail.setMaxLength(parserDetailRequest.getMaxLength());
        tblParserDetail.setMinLength(parserDetailRequest.getMinLength());
        tblParserDetail.setRefFieldName(parserDetailRequest.getRefFieldName());
        tblParserDetail.setSequence(BigDecimal.valueOf(parserDetailRequest.getSequence()));

        TblTransField tblTransField = new TblTransField();
        tblTransField.setTransFieldsId(parserDetailRequest.getTransFieldId());

        tblParserDetail.setTblTransField(tblTransField);

        tblParserDetail.setLastupdatedate(new Date());
        tblParserDetail.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblParserDetail.setUpdateindex(tblParserDetail.getUpdateindex() != null ? new BigDecimal(tblParserDetail.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblParserDetailRepo.saveAndFlush(tblParserDetail);


    }

    @Override
    public TblRemitMode saveRemitMode(TblRemitMode tblRemitMode) {
        return tblRemitModeRepo.saveAndFlush(tblRemitMode);
    }

    @Override
    public TblRemitMode updateRemitMode(UpdateRemitModeRequest updateRemitModeRequest, LoggedUserDetail loggedUserDetail) {
        TblRemitMode tblRemitMode = tblRemitModeRepo.findById(updateRemitModeRequest.getRemitModeId()).orElse(null);
        if(tblRemitMode!=null){

            tblRemitMode.setAmountCapping(updateRemitModeRequest.getAmountCapping());
            tblRemitMode.setIsActive(updateRemitModeRequest.getIsActive());
            tblRemitMode.setRemitCode(updateRemitModeRequest.getRemitCode());
            tblRemitMode.setRemitDefault(updateRemitModeRequest.getRemitDefault());
            tblRemitMode.setRemitDescr(updateRemitModeRequest.getRemitDescr());
            tblRemitMode.setLastupdatedate(new Date());
            tblRemitMode.setUpdateindex(tblRemitMode.getUpdateindex() != null ? new BigDecimal(tblRemitMode.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            tblRemitMode.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

            return tblRemitModeRepo.saveAndFlush(tblRemitMode);

        }
        else {
            return null;
        }
    }

    @Override
    public List<TblRemitMode> getAllRemittMode() {
        return tblRemitModeRepo.findAll();
    }


    @Override
    public List<TblParserDetail> findByTblParserHeadId(long parserHeadId) {
        List<TblParserDetail> tblParserDetails=tblParserDetailRepo.findByTblParserHeadParserHeadIdOrderBySequenceAsc(parserHeadId);
        if(tblParserDetails!=null && tblParserDetails.size()>0){
            return tblParserDetails;
        }
        else{
            return null;
        }
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
