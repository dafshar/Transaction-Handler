package com.transactionhandler.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionhandler.dal.AccountTransactionRepository;
import com.transactionhandler.dom.AccountTransaction;

@Component
public class Writer implements ItemWriter<AccountTransaction>{

	
	@Autowired
	public AccountTransactionRepository repository;

	@Override
	public void write(List<? extends AccountTransaction> items) throws Exception {
		// TODO Auto-generated method stub
		long i=1;
		for(AccountTransaction item:items)
		{
			System.out.println("Writer Item values " + item.getAccount_transaction_cd());
			repository.save(item);
			++i;
		}
	}

}
