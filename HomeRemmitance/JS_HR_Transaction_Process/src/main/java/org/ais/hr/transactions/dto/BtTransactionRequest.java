package org.ais.hr.transactions.dto;

import java.util.Date;
import java.util.List;

public class BtTransactionRequest {
    List<Long> fininstBankId;
    Date fromDate;
    Date toDate;

    public List<Long> getFininstBankId() {
        return fininstBankId;
    }

    public void setFininstBankId(List<Long> fininstBankId) {
        this.fininstBankId = fininstBankId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
