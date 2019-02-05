package com.transactionhandler.batch;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionhandler.dal.AccountTransactionRepository;
import com.transactionhandler.dom.AccountTransaction;
import com.transactionhandler.service.AccountTransactionServiceImpl;



@Component
public class Processor implements ItemProcessor<AccountTransaction, AccountTransaction> {

	@Autowired
	public AccountTransactionRepository repository;
	
	@Autowired
	public AccountTransactionServiceImpl service;


@Override
public AccountTransaction process(AccountTransaction acctTrans) throws Exception {

String pin = acctTrans.getPin();
	
System.out.println("Processing...");

//System.out.println("Find by PIN" + repository.findAllByPin("1234"));

System.out.println("Pin service:" + service.findCustSysIdByPin(pin));

return acctTrans;
}



}