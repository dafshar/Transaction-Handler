package service;

import gov.irs.irsm.controller.dao.ItemSummaryDAO;
import gov.irs.irsm.controller.domain.ItemSummary;
import gov.irs.irsm.events.core.EventExecutor;
import gov.irs.irsm.events.core.EventFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicBoolean;

import static gov.irs.irsm.controller.config.BaseDataSourceConfig.ADMIN_TRANSACTION_MANAGER_BEAN_NAME;

/**
 * This is an implementation of an {@link ItemSummaryHandler} that persists the
 * {@link ItemSummary} in the database after the job completes. This class
 * should be utilized if the ItemSummary needs to be persisted in the database.
 * 
 * @see gov.irs.irsm.controller.service.ItemTrackerService#process()
 */
public class DatabaseItemSummaryHandler implements ItemSummaryHandler {

    private static final EventExecutor EXECUTOR = EventFactory.getExecutor(DatabaseItemSummaryHandler.class);

    private final ItemSummaryDAO itemSummaryDAO;
    private final AtomicBoolean itemSummaryCreated = new AtomicBoolean(false);

    /**
     * @param itemSummaryDAOIn
     *            the DAO used to insert/update an ItemSummary in the database
     */
    public DatabaseItemSummaryHandler(final ItemSummaryDAO itemSummaryDAOIn) {
        itemSummaryDAO = itemSummaryDAOIn;
    }

    /**
     * Inserts or updates the ItemSummary in the database. The first call
     * inserts the second call updates.
     * 
     * @param itemSummary
     *            the item summary to insert or update in the database
     */
    @Override
    @Transactional(ADMIN_TRANSACTION_MANAGER_BEAN_NAME)
    public synchronized void handle(final ItemSummary itemSummary) {

        EXECUTOR.info("Persisting in database {}", itemSummary);
        if (itemSummaryCreated.get()) {
            itemSummaryDAO.update(itemSummary);
        } else {
            itemSummaryDAO.create(itemSummary);
            itemSummaryCreated.set(true);
        }
    }

    /**
     * @return the DAO used to insert/update an ItemSummary in the database
     */
    protected ItemSummaryDAO getItemSummaryDAO() {
        return itemSummaryDAO;
    }

}
