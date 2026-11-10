package org.ais.hr.transactions.esb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IbftPaymentRequest {
//    {
//        "MTI":"0200",
//            "PrimaryAccountNumber_002":"4220171508989",
//            "ProcessingCode_003":"410000",
//            "AmountTransaction_004": "000000010000",
//            "TransmissionDatetime_007":"0122070501",
//            "SystemsTraceAuditNumber_011":"000033",
//            "TimeLocalTransaction_012":"070501",
//            "DateLocalTransaction_013":"0122",
//            "MerchantType_018":"6041",
//            "AmountTransactionFee_028":"000000010000",
//            "AcquiringInstitutionIdentificationCode_032":"627197",
//            "PersonalIdentificationNumberData_052":"",
//            "AccountIdentification1_102":"0000102485",
//            "AccountIdentification2_103":"01020120311714145375",
//            "TransactionDescription_104":"TitleFetch- APIGEE",
//            "CNIC":"4220171508989",
//            "UserId":"IIB",
//            "BankName":"Bank AL HABIB LTD",
//            "BranchName":"Bank AL HABIB LTD",
//            "ConsumerTitle":"SYED ULFAT HUSSAIN JAFFRI",
//            "ReasonCode" :"305"
//    }
    @JsonProperty("MTI")
    public String mti;
    @JsonProperty("PrimaryAccountNumber_002")
    public String primaryAccountNumber_002;
    @JsonProperty("ProcessingCode_003")
    public String processingCode_003;
    @JsonProperty("AmountTransaction_004")
    public String amountTransaction_004;
    @JsonProperty("TransmissionDatetime_007")
    public String transmissionDatetime_007;
    @JsonProperty("SystemsTraceAuditNumber_011")
    public String systemsTraceAuditNumber_011;
    @JsonProperty("TimeLocalTransaction_012")
    public String timeLocalTransaction_012;
    @JsonProperty("DateLocalTransaction_013")
    public String dateLocalTransaction_013;
    @JsonProperty("MerchantType_018")
    public String merchantType_018;
    @JsonProperty("AmountTransactionFee_028")
    public String amountTransactionFee_028;
    @JsonProperty("AcquiringInstitutionIdentificationCode_032")
    public String acquiringInstitutionIdentificationCode_032;
    @JsonProperty("PersonalIdentificationNumberData_052")
    public String personalIdentificationNumberData_052;
    @JsonProperty("AccountIdentification1_102")
    public String accountIdentification1_102;
    @JsonProperty("AccountIdentification2_103")
    public String accountIdentification2_103;
    @JsonProperty("TransactionDescription_104")
    public String transactionDescription_104;
    @JsonProperty("CNIC")
    public String cnic;
    @JsonProperty("UserId")
    public String userId;
    @JsonProperty("BankName")
    public String bankName;
    @JsonProperty("BranchName")
    public String branchName;
    @JsonProperty("ConsumerTitle")
    public String consumerTitle;
    @JsonProperty("ReasonCode")
    public String reasonCode;


    public String getPrimaryAccountNumber_002() {
        return primaryAccountNumber_002;
    }

    public void setPrimaryAccountNumber_002(String primaryAccountNumber_002) {
        this.primaryAccountNumber_002 = primaryAccountNumber_002;
    }

    public String getProcessingCode_003() {
        return processingCode_003;
    }

    public void setProcessingCode_003(String processingCode_003) {
        this.processingCode_003 = processingCode_003;
    }

    public String getAmountTransaction_004() {
        return amountTransaction_004;
    }

    public void setAmountTransaction_004(String amountTransaction_004) {
        this.amountTransaction_004 = amountTransaction_004;
    }

    public String getTransmissionDatetime_007() {
        return transmissionDatetime_007;
    }

    public void setTransmissionDatetime_007(String transmissionDatetime_007) {
        this.transmissionDatetime_007 = transmissionDatetime_007;
    }

    public String getSystemsTraceAuditNumber_011() {
        return systemsTraceAuditNumber_011;
    }

    public void setSystemsTraceAuditNumber_011(String systemsTraceAuditNumber_011) {
        this.systemsTraceAuditNumber_011 = systemsTraceAuditNumber_011;
    }

    public String getTimeLocalTransaction_012() {
        return timeLocalTransaction_012;
    }

    public void setTimeLocalTransaction_012(String timeLocalTransaction_012) {
        this.timeLocalTransaction_012 = timeLocalTransaction_012;
    }

    public String getDateLocalTransaction_013() {
        return dateLocalTransaction_013;
    }

    public void setDateLocalTransaction_013(String dateLocalTransaction_013) {
        this.dateLocalTransaction_013 = dateLocalTransaction_013;
    }

    public String getMerchantType_018() {
        return merchantType_018;
    }

    public void setMerchantType_018(String merchantType_018) {
        this.merchantType_018 = merchantType_018;
    }

    public String getAmountTransactionFee_028() {
        return amountTransactionFee_028;
    }

    public void setAmountTransactionFee_028(String amountTransactionFee_028) {
        this.amountTransactionFee_028 = amountTransactionFee_028;
    }

    public String getAcquiringInstitutionIdentificationCode_032() {
        return acquiringInstitutionIdentificationCode_032;
    }

    public void setAcquiringInstitutionIdentificationCode_032(String acquiringInstitutionIdentificationCode_032) {
        this.acquiringInstitutionIdentificationCode_032 = acquiringInstitutionIdentificationCode_032;
    }

    public String getPersonalIdentificationNumberData_052() {
        return personalIdentificationNumberData_052;
    }

    public void setPersonalIdentificationNumberData_052(String personalIdentificationNumberData_052) {
        this.personalIdentificationNumberData_052 = personalIdentificationNumberData_052;
    }

    public String getAccountIdentification1_102() {
        return accountIdentification1_102;
    }

    public void setAccountIdentification1_102(String accountIdentification1_102) {
        this.accountIdentification1_102 = accountIdentification1_102;
    }

    public String getAccountIdentification2_103() {
        return accountIdentification2_103;
    }

    public void setAccountIdentification2_103(String accountIdentification2_103) {
        this.accountIdentification2_103 = accountIdentification2_103;
    }

    public String getTransactionDescription_104() {
        return transactionDescription_104;
    }

    public void setTransactionDescription_104(String transactionDescription_104) {
        this.transactionDescription_104 = transactionDescription_104;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getConsumerTitle() {
        return consumerTitle;
    }

    public void setConsumerTitle(String consumerTitle) {
        this.consumerTitle = consumerTitle;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
}
