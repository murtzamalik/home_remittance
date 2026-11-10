package org.ais.hr.transactions.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/26/2022
 * Time: 2:39 PM
 * Project : login
 */
public class ParkTransForAmndmntRequest {

    List<Long> transDetailIds = new ArrayList<>();

    public List<Long> getTransDetailIds() {
        return transDetailIds;
    }

    public void setTransDetailIds(List<Long> transDetailIds) {
        this.transDetailIds = transDetailIds;
    }
}
