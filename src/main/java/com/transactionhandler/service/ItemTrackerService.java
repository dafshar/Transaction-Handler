package com.transactionhandler.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.transactionhandler.dom.ItemTracker;



@Service
public class ItemTrackerService<V> {
	
	private final ItemTracker itemTracker;
	private long jobExecutionId;
    private final Set<V> inputItems = new HashSet<>();
    private final Set<V> processErrorItems = new HashSet<>();
    private int loggingInterval = 1;
    
	private static Logger logger = LoggerFactory.getLogger(ItemTrackerService.class);
	
    public ItemTrackerService(final ItemTracker itemTrackerIn) {
    	itemTracker = itemTrackerIn;
    }
    
    public void setJobExecutionId(final long jobExecutionIdIn) {
        jobExecutionId = jobExecutionIdIn;
    }
    
    public int getProcessErrorItemsSize() {
    	return processErrorItems.size();
    }
    

    
public synchronized void addProcessErrorItem(final V item) {
        
        if(item == null) {
            logger.warn("Cannot add null process error item to ItemTracker");
            return;
        }
        
        inputItems.add(item);
        processErrorItems.add(item);
    }


public synchronized void increaseInputItemCount(ChunkContext context) {
	
	int count = context.getStepContext().getStepExecution().getReadCount();
				
	if (count > 0 && count % loggingInterval == 0) {			
		itemTracker.setItemCount(count);
		logger.info("Input Item count: " + itemTracker.getItemCount());
	}
}

public synchronized void increaseProcessErrorItemCount(ChunkContext context) {
	
	int count = context.getStepContext().getStepExecution().getProcessSkipCount();
				
	if (count > 0 && count % loggingInterval == 0) {			
		itemTracker.setProcessErrorCount(count);
		logger.info("Processor Exception Skip Count: " + itemTracker.getProcessErrorCount());
	}
}

public synchronized void addJobExecutionId(ChunkContext context) {
	
	Long jobExecutionId = context.getStepContext().getStepExecution().getJobExecutionId();
				 		
		itemTracker.setJobExecutionId(jobExecutionId);;
		logger.info("Job Execution Id : " + itemTracker.getJobExecutionId());
	
}




public ItemTracker buildItemTracker() {
	itemTracker.setJobExecutionId(jobExecutionId);
	itemTracker.setItemCount(inputItems.size());
	itemTracker.setProcessErrorCount(processErrorItems.size());
    return itemTracker;
}

}
