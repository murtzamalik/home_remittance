package org.ais.hr.transactions.esb;

import org.ais.hr.transactions.Service.ProcessTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 4/22/2022
 * Time: 4:12 PM
 * Project : Transaction_Process
 */


@Component
public class JsTransactionApi  {

    @Autowired
   private ProcessTransactionService processTransactionService;



}
