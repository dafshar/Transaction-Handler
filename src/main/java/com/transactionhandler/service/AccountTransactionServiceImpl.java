package com.transactionhandler.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class AccountTransactionServiceImpl implements AccountTransactionService{
	
	@Autowired
    EntityManager entityManager;
	
	
	
	 @Override
	    public String findCustSysIdByPin(String pin) {
	        try {
	            String sql = "select cust.customer_sys_id from Customer cust, AccountTransaction acct \r\n" + 
	            		"where cust.pin="+pin+"and cust.validity_cd=acct.validity_cd";
	            Query query = entityManager.createQuery(sql);
	            return (String) query.getSingleResult();
	        } catch (NoResultException e) {
	        	System.out.println("Customer does not exist");
	            return null;
	        }
	    }

}
