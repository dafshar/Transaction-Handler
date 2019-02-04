package com.transactionhandler.batch;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionhandler.dal.AccountTransactionEntity;
import com.transactionhandler.dal.AccountTransactionRepository;



@Component
public class Processor implements ItemProcessor<AccountTransactionEntity, AccountTransactionEntity> {

	@Autowired
	public AccountTransactionRepository repository;


@Override
public AccountTransactionEntity process(AccountTransactionEntity acctTrans) throws Exception {

System.out.println("Processing...");

System.out.println("Find by PIN" + repository.findAllByPin("1234"));


return acctTrans;
}



}