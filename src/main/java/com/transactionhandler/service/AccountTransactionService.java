package com.transactionhandler.service;

import java.util.List;

import com.transactionhandler.dal.AccountTransactionEntity;
import com.transactionhandler.dal.AccountTransactionRepository;

public interface AccountTransactionService{
    
    List<AccountTransactionEntity> findAllByPin(String account_transaction_sys_id);
    
}
