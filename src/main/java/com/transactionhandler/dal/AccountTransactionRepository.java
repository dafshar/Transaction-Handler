package com.transactionhandler.dal;

import java.util.List;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.transactionhandler.dom.AccountTransaction;


public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, String>{
	
	void delete(AccountTransaction deleted);
	 
    List<AccountTransaction> findAll();
    
    List<AccountTransaction> findAllByPin(String pin);
    
 
    @SuppressWarnings("unchecked")
	AccountTransaction save(AccountTransaction persisted);
    
    AccountTransaction saveAndFlush(AccountTransaction entity);
    
    /*
    @Query("SELECT account_transaction_sys_id FROM account_transaction where quality_sys_id='Sysidqual'")
    String getSysId();
    */
 


    
    
	

}
