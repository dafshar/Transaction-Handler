package com.transactionhandler.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	 @Column(name = "customer_sys_id", nullable = false)
	 private String customer_sys_id;
	
	
	 @Column(name = "account_transaction_sys_id", nullable = false)
	 private String account_transaction_sys_id;
	 
	 @Column(name = "pin", nullable = false)
	 private String pin;
	 
	 @Column(name = "memo_freeze_cd", nullable = false)
	 private int memo_freeze_cd;
	 
	 @Column(name = "validity_cd", nullable = false)
	 private int validity_cd;
	 
	 public Customer() {}
	 
	 public String getCustomer_sys_id() {
		  return customer_sys_id;
		 }
	 
	 public void setCustomer_sys_id(String customer_sys_id) {
		  this.customer_sys_id = customer_sys_id;
		 }
	 
	 public String getAccount_transaction_sys_id() {
		  return account_transaction_sys_id;
		 }
	 
	 public void setAccount_transaction_sys_id(String account_transaction_sys_id) {
		  this.account_transaction_sys_id = account_transaction_sys_id;
		 }
	 
	 public String getPin() {
		  return pin;
		  
		 }
	 public void setPin(String pin) {
		  this.pin = pin;
		 }  
	 
	 public int getMemo_freeze_cd() {
		  return memo_freeze_cd;
		 }
	 
	 public void setMemo_freeze_cd(int memo_freeze_cd) {
		  this.memo_freeze_cd = memo_freeze_cd;
		 }  
	 
	 public int getValidity_cd() {
		  return validity_cd;
		 }
	 
	 public void setValidity_cd(int validity_cd) {
		  this.validity_cd = validity_cd;
		 }  
	 
	 @Override

	    public String toString(){

	            return "Customer DOM contents: " + customer_sys_id;

	        }
	 
	 

}
