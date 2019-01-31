package com.transactionhandler.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;


public interface AccountTransactionRepository extends JpaRepository<AccountTransactionEntity, Long>{
	
	void delete(AccountTransactionEntity deleted);
	 
    List<AccountTransactionEntity> findAll();
 
    @SuppressWarnings("unchecked")
	AccountTransactionEntity save(AccountTransactionEntity persisted);
    
    AccountTransactionEntity saveAndFlush(AccountTransactionEntity entity);
    
    
	

}
