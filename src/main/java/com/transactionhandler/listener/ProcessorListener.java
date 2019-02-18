package com.transactionhandler.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.transactionhandler.dom.ItemTracker;
import com.transactionhandler.service.ItemTrackerService;


public class ProcessorListener implements ChunkListener{
	
	@Autowired
	ItemTracker items;
	
	@Autowired
	ItemTrackerService itemTrackerService;
	
	private static final Logger logger = LogManager.getLogger(ProcessorListener.class);
		
	@Override
	public void beforeChunk(ChunkContext context) {
		logger.info("Before Processor Chunk");

	}

	@Override
	public void afterChunk(ChunkContext context) {

		itemTrackerService.increaseInputItemCount(context);
		itemTrackerService.addJobExecutionId(context);
		itemTrackerService.increaseProcessErrorItemCount(context);
		
		logger.info("Item Tracker Output: " + items);
	}
	
	@Override
	public void afterChunkError(ChunkContext context) {

		logger.info("There is some chunk exception");

		
	}
}