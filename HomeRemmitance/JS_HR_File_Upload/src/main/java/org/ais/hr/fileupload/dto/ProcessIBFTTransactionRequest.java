package org.ais.hr.fileupload.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/26/2022
 * Time: 2:32 PM
 * Project : login
 */
public class ProcessIBFTTransactionRequest {


    List<Long> transDetailIds = new ArrayList<>();

    public List<Long> getTransDetailIds() {
        return transDetailIds;
    }

    public void setTransDetailIds(List<Long> transDetailIds) {
        this.transDetailIds = transDetailIds;
    }
}
