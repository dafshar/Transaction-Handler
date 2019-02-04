package com.transactionhandler.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.transactionhandler.dal.AccountTransactionEntity;

@Repository
public class AccountTransactionServiceImpl implements AccountTransactionService{
	
	@Autowired
    EntityManager entityManager;
	
	
	
	 @Override
	    public List<AccountTransactionEntity> findAllByPin(String pin) {
	        try {
	            String sql = "SELECT account_transaction_sys_id FROM account_transaction where pin='1234' ";
	            Query query = entityManager.createQuery(sql);
	            return (List<AccountTransactionEntity>) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

}
