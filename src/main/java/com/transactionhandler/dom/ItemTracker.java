package com.transactionhandler.dom;

import org.springframework.stereotype.Component;

@Component
public class ItemTracker {
	
	private long jobExecutionId;
    private int itemCount;
    private int processErrorCount;
    
    private ItemTracker() {}
    

	 	public long getJobExecutionId() {
		  return jobExecutionId;
		 }

		 public void setJobExecutionId(Long jobExecutionId) {
		  this.jobExecutionId = jobExecutionId;
		 }
		 
		 public int getItemCount() {
			  return itemCount;
			 }

		 public void setItemCount(int itemCount) {
			  this.itemCount = itemCount;
			 }
		 
		 public int getProcessErrorCount() {
			  return processErrorCount;
			 }

		 public void setProcessErrorCount(int processErrorCount) {
			  this.processErrorCount = processErrorCount;
			 }
		 
		 @Override

		    public String toString(){

		            return "Item Tracker: " + "jobExecutionId: " + jobExecutionId +" " + "itemCount: " + itemCount + " "+ "processErrorCount: " + processErrorCount;

		        }
    
        
    }



