package service;

import gov.irs.irsm.controller.domain.ItemSummary;

/**
 * This is called by the ItemSummaryTrackerService to perform actions on the
 * provided {@link ItemSummary}. The ItemSummaryTrackerService is called after
 * the job completes.
 * 
 * @see gov.irs.irsm.controller.service.ItemTrackerService#process()
 */
public interface ItemSummaryHandler {

    /**
     * Performs actions on the provided {@link ItemSummary}
     * 
     * @param itemSummary
     *            the item summary
     */
    void handle(final ItemSummary itemSummary);

}
