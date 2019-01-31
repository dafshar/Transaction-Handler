package com.transactionhandler.dal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_transaction")
public class AccountTransactionEntity {
	
	private static final long serialVersionUID = 4865903039190150223L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	 @Column(name = "account_transaction_origination_dt", nullable = false)
	 private Date account_transaction_origination_dt;
	 
	 @Column(name = "account_transaction_sys_id", nullable = false)
	 private String account_transaction_sys_id;
	 
	 @Column(name = "account_transaction_cd", nullable = false)
	 private int account_transaction_cd;
	 
	 @Column(name = "account_transaction_dt", nullable = false)
	 private Date account_transaction_dt;
	 
	 @Column(name = "account_transaction_trans_subtyp_cd", nullable = true)
	 private int account_transaction_trans_subtyp_cd;
	 
	 @Column(name = "correspondence_dt", nullable = true)
	 private Date correspondence_dt;
	 
	 @Column(name = "quality_cd", nullable = false)
	 private int quality_cd;
	 
	 @Column(name = "quality_sys_id", nullable = true)
	 private String quality_sys_id;
	 
	 @Column(name = "document_number", nullable = true)
	 private String document_number;
	 
	 @Column(name = "ext_source", nullable = false)
	 private String ext_source;
	 
	 @Column(name = "identification_cd", nullable = false)
	 private int identification_cd;
	 
	 @Column(name = "input_file", nullable = true)
	 private String input_file;
	 
	 @Column(name = "partition_sys_id", nullable = false)
	 private String partition_sys_id;
	 
	 @Column(name = "period_end_dt", nullable = true)
	 private Date period_end_dt;
	 
	 @Column(name = "posted_cyc_id", nullable = true)
	 private String posted_cyc_id;
	 
	 @Column(name = "pin", nullable = false)
	 private String pin;
	 
	 @Column(name = "unpostable_cd", nullable = true)
	 private int unpostable_cd;
	 
	 @Column(name = "unpostable_rsn_cd", nullable = true)
	 private int unpostable_rsn_cd;
	 
	 @Column(name = "updated_by_trans", nullable = false)
	 private String updated_by_trans;
	 
	 @Column(name = "updated_ts", nullable = false)
	 private Date updated_ts;
	 
	 @Column(name = "validity_cd", nullable = false)
	 private int validity_cd;
	 
	 public AccountTransactionEntity() {}
	 
	 public AccountTransactionEntity( Date account_transaction_origination_dt,
			  String account_transaction_sys_id,
			  int account_transaction_cd,
			  Date account_transaction_dt,
			  int account_transaction_trans_subtyp_cd,
			  Date correspondence_dt,
			  int quality_cd,
			  String quality_sys_id,
			  String document_number,
			  String ext_source,
			  int identification_cd,
			  String input_file,
			  String partition_sys_id,
			  Date period_end_dt,
			  String posted_cyc_id,
			  String pin,
			  int unpostable_cd,
			  int unpostable_rsn_cd,
			  String updated_by_trans,
			  Date updated_ts,
			  int validity_cd) {
		 this.account_transaction_origination_dt = account_transaction_origination_dt;
		 this.account_transaction_sys_id = account_transaction_sys_id;
		 this.account_transaction_cd = account_transaction_cd;
		 this.account_transaction_dt = account_transaction_dt;
		 this.account_transaction_trans_subtyp_cd = account_transaction_trans_subtyp_cd;
		 this.correspondence_dt = correspondence_dt;
		 this.quality_cd = quality_cd;
		 this.quality_sys_id = quality_sys_id;
		 this.document_number = document_number;
		 this.ext_source = ext_source;
		 this.identification_cd = identification_cd;
		 this.input_file = input_file;
		 this.partition_sys_id = partition_sys_id;
		 this.period_end_dt = period_end_dt;
		 this.posted_cyc_id = posted_cyc_id;
		 this.pin = pin;
		 this.unpostable_cd = unpostable_cd;
		 this.unpostable_rsn_cd = unpostable_rsn_cd;
		 this.updated_by_trans = updated_by_trans;
		 this.updated_ts = updated_ts;
		 this.validity_cd = validity_cd;
	    }


	 public Date getAccount_transaction_origination_dt() {
	  return account_transaction_origination_dt;
	 }

	 public void setAccount_transaction_origination_dt(Date account_transaction_origination_dt) {
	  this.account_transaction_origination_dt = account_transaction_origination_dt;
	 }
	 
	 public String getAccount_transaction_sys_id() {
		  return account_transaction_sys_id;
		 }
	 
	 public void setAccount_transaction_sys_id(String account_transaction_sys_id) {
		  this.account_transaction_sys_id = account_transaction_sys_id;
		 }
	 
	 public int getAccount_transaction_cd() {
		  return account_transaction_cd;
		 }
	 
	 public void setAccount_transaction_cd(int account_transaction_cd) {
		  this.account_transaction_cd = account_transaction_cd;
		 }
	 
	 public Date getAccount_transaction_dt() {
		  return account_transaction_dt;
		 }
	 
	 public void setAccount_transaction_dt(Date account_transaction_dt) {
		  this.account_transaction_dt = account_transaction_dt;
		 }
	 
	 public int getAccount_transaction_trans_subtyp_cd() {
		  return account_transaction_trans_subtyp_cd;
		 }
	 
	 public void setAccount_transaction_trans_subtyp_cd(int account_transaction_trans_subtyp_cd) {
		  this.account_transaction_trans_subtyp_cd = account_transaction_trans_subtyp_cd;
		 }
	 
	 public Date getCorrespondence_dt() {
		  return correspondence_dt;
		 }
	 
	 public void setCorrespondence_dt(Date correspondence_dt) {
		  this.correspondence_dt = correspondence_dt;
		 }
	 
	 public int getQuality_cd() {
		  return quality_cd;
		 }
	 
	 public void setQuality_cd(int quality_cd) {
		  this.quality_cd = quality_cd;
		 }
	 
	 public String getQuality_sys_id() {
		  return quality_sys_id;
		 }
	 
	 public void setQuality_sys_id(String quality_sys_id) {
		  this.quality_sys_id = quality_sys_id;
		 }
	 
	 public String getDocument_number() {
		  return document_number;
		 }
	 
	 public void setDocument_number(String document_number) {
		  this.document_number = document_number;
		 }
	 
	 public String getExt_source() {
		  return ext_source;
		 }
	 
	 public void setExt_source(String ext_source) {
		  this.ext_source = ext_source;
		 }

	 public int getIdentification_cd() {
		  return identification_cd;
		 }
	 
	 public void setIdentification_cd(int identification_cd) {
		  this.identification_cd = identification_cd;
		 }
	 
	 public String getInput_file() {
		  return input_file;
		 }
	 
	 public void setInput_file(String input_file) {
		  this.input_file = input_file;
		 }
	 
	 public String getPartition_sys_id() {
		  return partition_sys_id;
		 }
	 
	 public void setPartition_sys_id(String partition_sys_id) {
		  this.partition_sys_id = partition_sys_id;
		 }
	 
	 public Date getPeriod_end_dt() {
		  return period_end_dt;
		 }
	 
	 public void setPeriod_end_dt(Date period_end_dt) {
		  this.period_end_dt = period_end_dt;
		 } 
	 
	 public String getPosted_cyc_id() {
		  return posted_cyc_id;
		 }
	 
	 public void setPosted_cyc_id(String posted_cyc_id) {
		  this.posted_cyc_id = posted_cyc_id;
		 }  
	 
	 public String getPin() {
		  return pin;
		 }
	 public void setPin(String pin) {
		  this.pin = pin;
		 }  

	 public int getUnpostable_cd() {
		  return unpostable_cd;
		 }
	 
	 public void setUnpostable_cd(int unpostable_cd) {
		  this.unpostable_cd = unpostable_cd;
		 }  
	 
	 public int getUnpostable_rsn_cd() {
		  return unpostable_rsn_cd;
		 }
	 
	 public void setUnpostable_rsn_cd(int unpostable_rsn_cd) {
		  this.unpostable_rsn_cd = unpostable_rsn_cd;
		 }  
	 
	 public String getUpdated_by_trans() {
		  return updated_by_trans;
		 }
	 
	 public void setUpdated_by_trans(String updated_by_trans) {
		  this.updated_by_trans = updated_by_trans;
		 }  
	 
	 public Date getUpdated_ts() {
		  return updated_ts;
		 }
	 
	 public void setUpdated_ts(Date updated_ts) {
		  this.updated_ts = updated_ts;
		 }   
	 
	 public int getValidity_cd() {
		  return validity_cd;
		 }
	 
	 public void setValidity_cd(int validity_cd) {
		  this.validity_cd = validity_cd;
		 }  
	 
	    @Override

	    public String toString(){

	            return "Account Transaction DOM contents: " + account_transaction_sys_id;

	        }

	 
	 
}
