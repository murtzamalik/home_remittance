package org.ais.hr.transactions.dto;

public class ProcessTransactionsResponse {
    String totalTransactions;
    String sucessfulTransactions;
    String unsucessfullIFT;
    String unSucessfullIBFT;

    public String getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(String totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public String getSucessfulTransactions() {
        return sucessfulTransactions;
    }

    public void setSucessfulTransactions(String sucessfulTransactions) {
        this.sucessfulTransactions = sucessfulTransactions;
    }

    public String getUnsucessfullIFT() {
        return unsucessfullIFT;
    }

    public void setUnsucessfullIFT(String unsucessfullIFT) {
        this.unsucessfullIFT = unsucessfullIFT;
    }

    public String getUnSucessfullIBFT() {
        return unSucessfullIBFT;
    }

    public void setUnSucessfullIBFT(String unSucessfullIBFT) {
        this.unSucessfullIBFT = unSucessfullIBFT;
    }
}
