package org.ais.hr.transactions.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 6/13/2022
 * Time: 5:04 PM
 * Project : Transaction_Process
 */
public class TransactionsInqStatusWiseRequest {

    private long transHeadId;

    private String statusCode;

    public long getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(long transHeadId) {
        this.transHeadId = transHeadId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
