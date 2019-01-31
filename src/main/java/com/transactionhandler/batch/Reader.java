package com.transactionhandler.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.transactionhandler.dal.AccountTransactionEntity;

public class Reader extends FlatFileItemReader<AccountTransactionEntity> {
	
	public Reader(Resource resource) {
		
		super();
		
		setResource(resource);
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "account_transaction_origination_dt", "account_transaction_sys_id",
				"account_transaction_cd", "account_transaction_dt",
				"account_transaction_trans_subtyp_cd", "correspondence_dt", "quality_cd",
				"quality_sys_id", "document_number", "ext_source", "identification_cd", "input_file",
				"partition_sys_id", "period_end_dt", "posted_cyc_id", "pin", "unpostable_cd",
				"unpostable_rsn_cd", "updated_by_trans", "updated_ts", "validity_cd" });
		lineTokenizer.setDelimiter(",");
	    lineTokenizer.setStrict(false);
	    
	    BeanWrapperFieldSetMapper<AccountTransactionEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(AccountTransactionEntity.class);

		DefaultLineMapper<AccountTransactionEntity> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(defaultLineMapper);
	}
}