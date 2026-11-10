package org.ais.hr.transactions.dto;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 6/13/2022
 * Time: 4:47 PM
 * Project : Transaction_Process
 */
public class FinInstWiseFileNameResponse {


    private long transHeadId;

    private String fileName;

    public long getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(long transHeadId) {
        this.transHeadId = transHeadId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
