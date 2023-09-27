package bug.ehcache3195;

import bug.ehcache3195.events.NewCompanyTxEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 6:04 AM
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class EventsBoundedToTx {

    private final ApplicationEventPublisher eventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void handleNewCompanyTxEvent(NewCompanyTxEvent event) {
        Utils.checkTxNotExists();

        log.info("call EventsBoundedToTx.handleNewCompanyTxEvent(name: {}, tx exists: {})", event.name, TransactionSynchronizationManager.isActualTransactionActive());
        eventPublisher.publishEvent(event.to());
    }

}
