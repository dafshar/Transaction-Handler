package service;

import gov.irs.irsm.controller.domain.ItemSummary;
import gov.irs.irsm.events.core.EventExecutor;
import gov.irs.irsm.events.core.EventFactory;

/**
 * This is an implementation of an {@link ItemSummaryHandler} that logs the
 * {@link ItemSummary} after the job completes.
 * 
 * @see gov.irs.irsm.controller.service.ItemTrackerService#process()
 */
public class LoggingItemSummaryHandler implements ItemSummaryHandler {

    private static final EventExecutor EXECUTOR = EventFactory.getExecutor(LoggingItemSummaryHandler.class);

    /**
     * Logs the ItemSummary
     * 
     * @param itemSummary
     *            the item summary to log
     */
    @Override
    public void handle(final ItemSummary itemSummary) {
        EXECUTOR.info("{}", itemSummary);
    }

}
