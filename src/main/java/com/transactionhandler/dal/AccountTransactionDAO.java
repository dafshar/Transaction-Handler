package com.transactionhandler.dal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;



/*
@Entity
@Table(name = "account_transaction")
*/
public class AccountTransactionDAO {
	
	@Autowired
	AccountTransactionRepository repository ;

	public void saveAccountTransactionEntity(AccountTransactionEntity entity){
		repository.save(entity);
	}
	 
}
