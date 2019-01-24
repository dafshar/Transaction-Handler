package dom;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class AccountTransaction {
	
	 private Date account_transaction_origination_dt;
	 private String account_transaction_sys_id;
	 private int account_transaction_cd;
	 private Date account_transaction_dt;
	 private int account_transaction_trans_subtyp_cd;
	 private Date correspondence_dt;
	 private int quality_cd;
	 private String quality_sys_id;
	 private String document_number;
	 private String ext_source;
	 private int identification_cd;
	 private String input_file;
	 private String partition_sys_id;
	 private Date period_end_dt;
	 private String posted_cyc_id;
	 private String pin;
	 private int unpostable_cd;
	 private int unpostable_rsn_cd;
	 private String updated_by_trans;
	 private Date updated_ts;
	 private int validity_cd;

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

	 
	 
}
