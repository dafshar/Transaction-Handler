package service;

//import gov.irs.irsm.controller.domain.ItemSummary;
//import gov.irs.irsm.events.core.EventExecutor;
//import gov.irs.irsm.events.core.EventFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service used to track the number of items that have and have not been
 * processed, as well as the number of items that resulted in a
 * read/process/write error. The previous data is used to construct a
 * {@link ItemSummary} object which is sent to a {@link ItemSummaryHandler} when
 * the "process" method is called. The "process" method is called after the job
 * completes.
 * 
 * @param <V>
 *            the type of item to track
 */
public class ItemTrackerService<V> {

    //private static final EventExecutor EXECUTOR = EventFactory.getExecutor(ItemTrackerService.class);
    
    private final ItemSummaryHandler itemSummaryHandler;
    private long jobExecutionId;
    private final Set<V> inputItems = new HashSet<>();
    private final Set<V> processedItems = new HashSet<>();
    private final Set<V> unprocessedItems = new HashSet<>();
    private final Set<V> writtenItems = new HashSet<>();
    private final Set<V> processErrorItems = new HashSet<>();
    private final Set<V> writeErrorItems = new HashSet<>();
    private final AtomicLong readErrorCount = new AtomicLong(0);

    /**
     * @param itemSummaryHandlerIn
     *            the handler to perform actions on the {@link ItemSummary}
     */
    public ItemTrackerService(final ItemSummaryHandler itemSummaryHandlerIn) {
        itemSummaryHandler = itemSummaryHandlerIn;
    }

    /**
     * @param jobExecutionIdIn
     *            the unique job execution id for the current job
     */
    public void setJobExecutionId(final long jobExecutionIdIn) {
        jobExecutionId = jobExecutionIdIn;
    }

    /**
     * Adds the item to the tracker to indicate that it is new item and is
     * unprocessed. This is the first method to call.
     * 
     * @param item
     *            the item to add
     */
    public synchronized void addInputItem(final V item) {
        
        if(item == null) {
            //EXECUTOR.warn("Cannot add null input item to ItemTracker");
            return;
        }
        
        inputItems.add(item);
        processedItems.remove(item);
        unprocessedItems.add(item);
        writtenItems.remove(item);
        processErrorItems.remove(item);
        writeErrorItems.remove(item);
    }

    /**
     * Adds the item to the tracker to indicate that it was processed and
     * written successfully
     * 
     * @param item
     *            the item to add
     */
    public synchronized void addWrittenItem(final V item) {
        
        if(item == null) {
           // EXECUTOR.warn("Cannot add null written item to ItemTracker");
            return;
        }
        
        inputItems.add(item);
        processedItems.add(item);
        unprocessedItems.remove(item);
        writtenItems.add(item);
        processErrorItems.remove(item);
        writeErrorItems.remove(item);
    }

    /**
     * Adds the item to the tracker to indicate the it is unprocessed and a
     * process error occurred
     * 
     * @param item
     *            the item to add
     */
    public synchronized void addProcessErrorItem(final V item) {
        
        if(item == null) {
            //EXECUTOR.warn("Cannot add null process error item to ItemTracker");
            return;
        }
        
        inputItems.add(item);
        processedItems.remove(item);
        unprocessedItems.add(item);
        writtenItems.remove(item);
        processErrorItems.add(item);
        writeErrorItems.remove(item);
    }

    /**
     * Adds the item to the tracker to indicate the it is unprocessed and a
     * write error occurred
     * 
     * @param item
     *            the item to add
     */
    public synchronized void addWriteErrorItem(final V item) {
        
        if(item == null) {
            //EXECUTOR.warn("Cannot add null write error item to ItemTracker");
            return;
        }
        
        inputItems.add(item);
        processedItems.remove(item);
        unprocessedItems.add(item);
        writtenItems.remove(item);
        processErrorItems.remove(item);
        writeErrorItems.add(item);
    }

    /**
     * Increments the number of times an error occurred while reading item data
     */
    public synchronized void incrementReadErrorCount() {
        readErrorCount.incrementAndGet();
    }

    /**
     * Builds an {@link ItemSummary} from the current tracker data and sends it
     * to the {@link ItemSummaryHandler} 
     */
    public synchronized void process() {
        itemSummaryHandler.handle(buildItemSummary());
    }

    /**
     * Builds and returns an {@link ItemSummary} from the current tracker data
     * 
     * @return the item summary
     */
    public synchronized ItemSummary getItemSummary() {
        return buildItemSummary();
    }

    private ItemSummary buildItemSummary() {
        return ItemSummary
                .builder()
                .withJobExecutionId(jobExecutionId)
                .withItemCount(inputItems.size())
                .withProcessedItemCount(processedItems.size())
                .withUnprocessedItemCount(unprocessedItems.size())
                .withWrittenItemCount(writtenItems.size())
                .withReadErrorCount(readErrorCount.get())
                .withProcessErrorCount(processErrorItems.size())
                .withWriteErrorCount(writeErrorItems.size())
                .build();
    }

}
