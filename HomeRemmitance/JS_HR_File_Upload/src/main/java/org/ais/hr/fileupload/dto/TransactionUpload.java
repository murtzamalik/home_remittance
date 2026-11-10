package org.ais.hr.fileupload.dto;

import java.util.List;

public class TransactionUpload {

    int remitId;
    List<Transaction> transactionList;

    public int getRemitId() {
        return remitId;
    }

    public void setRemitId(int remitId) {
        this.remitId = remitId;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
