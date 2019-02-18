package com.transactionhandler.batch;


import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionhandler.dal.AccountTransactionRepository;
import com.transactionhandler.dom.AccountTransaction;
import com.transactionhandler.listener.ProcessorListener;
import com.transactionhandler.service.AccountTransactionServiceImpl;



@Component
public class Processor implements ItemProcessor<AccountTransaction, AccountTransaction> {

	@Autowired
	public AccountTransactionRepository repository;
	
	@Autowired
	public AccountTransactionServiceImpl service;

	private static final Logger logger = LogManager.getLogger(Processor.class);

@Override
public AccountTransaction process(AccountTransaction acctTrans) throws Exception {
	String pin = acctTrans.getPin();

	System.out.println("Processing...");
	

		if(!pin.equals("1234")) {
		service.findCustSysIdByPin(pin);
		
		}
		
		else {
	
		throw new Exception("Bad Pin");
			
		}
		return acctTrans;


		}
	



}