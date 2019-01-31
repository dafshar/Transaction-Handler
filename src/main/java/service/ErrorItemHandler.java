package service;

import java.util.Collection;

/**
 * Performs actions on the collection of items which encountered errors during
 * batch job execution. This is called by the ErrorTrackerService after the job
 * completes.
 * 
 * @see ErrorTrackerService
 * @see gov.irs.irsm.controller.listener.DefaultJobListener
 * @param <V>
 *            the type of item to process
 */
public interface ErrorItemHandler<V> {

    /**
     * Performs actions on the collection of items which encountered errors
     * during batch job execution
     * 
     * @param items
     *            the collection of items which encountered errors
     */
    void handle(final Collection<V> items);

}
