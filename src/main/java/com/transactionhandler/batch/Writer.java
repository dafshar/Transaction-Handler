package com.transactionhandler.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionhandler.app.JobRunner;
import com.transactionhandler.dal.AccountTransactionEntity;
import com.transactionhandler.dal.AccountTransactionRepository;

@Component
public class Writer implements ItemWriter<AccountTransactionEntity>{

	
	@Autowired
	public AccountTransactionRepository repository;
	
	private static Logger logger = LoggerFactory.getLogger(Writer.class);

	@Override
	public void write(List<? extends AccountTransactionEntity> items) throws Exception {
		// TODO Auto-generated method stub
		long i=1;
		for(AccountTransactionEntity item:items)
		{
			logger.info("Item values " + item.getAccount_transaction_cd());
			repository.save(item);
			++i;
		}
	}

}
