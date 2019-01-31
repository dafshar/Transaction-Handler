package service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Errors can occur while Spring Batch is processing items in ItemReaders,
 * ItemWriters, and ItemProcessors. This service provides the functionality to
 * track which items encountered errors while the job was processing them. It
 * also provides a "process" method which will call an {@link ErrorItemHandler}
 * to perform error processing actions on the failed items.
 * 
 * If the DefaultJobConfig is used, an ErrorHandlingJobListener will check,
 * after the job finishes executing, if there are any error items in the
 * tracker. If there are error items in the tracker, the "process" method will
 * be called and the job will be marked as failed.
 *
 * @see gov.irs.irsm.controller.config.DefaultJobConfig
 * @see gov.irs.irsm.controller.listener.DefaultJobListener
 * @param <V>
 *            the type of item to track
 */
public class ErrorTrackerService<V> {

   // private static final EventExecutor EXECUTOR = EventFactory.getExecutor(ErrorTrackerService.class);

    private final ErrorItemHandler<V> errorItemHandler;
    private final Set<V> items = new HashSet<>();

    /**
     * @param errorItemHandlerIn
     *            the handler to perform actions on the collection of items
     *            which encountered errors
     */
    public ErrorTrackerService(final ErrorItemHandler<V> errorItemHandlerIn) {
        errorItemHandler = errorItemHandlerIn;
    }

    /**
     * Adds the item which encountered an error during job processing to the
     * tracker
     * 
     * @param item
     *            the item which encountered an error
     */
    public synchronized void add(final V item) {

        if (item == null) {
            //EXECUTOR.warn("Cannot add a null error item. Not adding.");
            return;
        }

        //EXECUTOR.debug("Adding error item to list. Item: {}", item);
        items.add(item);
    }

    /**
     * Adds the collection of items which encountered errors during job
     * processing to the tracker
     * 
     * @param itemsIn
     *            the collection of items which encountered errors
     */
    public synchronized void addAll(final Collection<V> itemsIn) {

        if (itemsIn == null || itemsIn.isEmpty()) {
            //EXECUTOR.warn("Cannot add a null or empty list of error items. Not adding.");
            return;
        }

       // EXECUTOR.debug("Adding error items to list, Items: {}", itemsIn);
        items.addAll(itemsIn);
    }

    /**
     * Calls the {@link ErrorItemHandler} to perform actions on all the items in
     * the tracker. The items in the tracker are cleared out after this method
     * is called.
     */
    public synchronized void process() {
        errorItemHandler.handle(new ArrayList<>(items));
        items.clear();
    }

    /**
     * @return whether the tracker contains items or does not contain items
     */
    public synchronized boolean hasItems() {
        return items.size() > 0;
    }

    /**
     * @return an unmodifiable set of all the current items in tracker
     */
    public Set<V> getItems() {
        return Collections.unmodifiableSet(items);
    }

}
