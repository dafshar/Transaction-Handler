package com.transactionhandler.dal;

import java.util.List;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;


public interface AccountTransactionRepository extends JpaRepository<AccountTransactionEntity, String>{
	
	void delete(AccountTransactionEntity deleted);
	 
    List<AccountTransactionEntity> findAll();
    
    List<AccountTransactionEntity> findAllByPin(String pin);
    
 
    @SuppressWarnings("unchecked")
	AccountTransactionEntity save(AccountTransactionEntity persisted);
    
    AccountTransactionEntity saveAndFlush(AccountTransactionEntity entity);
    
    /*
    @Query("SELECT account_transaction_sys_id FROM account_transaction where quality_sys_id='Sysidqual'")
    String getSysId();
    */
 


    
    
	

}
