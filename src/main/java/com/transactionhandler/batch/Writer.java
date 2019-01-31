package com.transactionhandler.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionhandler.dal.AccountTransactionEntity;
import com.transactionhandler.dal.AccountTransactionRepository;

@Component
public class Writer implements ItemWriter<AccountTransactionEntity>{

	
	@Autowired
	public AccountTransactionRepository repository;

	@Override
	public void write(List<? extends AccountTransactionEntity> items) throws Exception {
		// TODO Auto-generated method stub
		long i=1;
		for(AccountTransactionEntity item:items)
		{
			System.out.println("Item values " + item.getAccount_transaction_cd());
			repository.save(item);
			++i;
		}
	}

}
