package com.transactionhandler.service;

import java.util.List;
import com.transactionhandler.dal.AccountTransactionRepository;


public interface AccountTransactionService{
    
	String findCustSysIdByPin(String pin);
    
}
