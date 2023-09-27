package bug.ehcache3195;

import bug.ehcache3195.events.NewCompanyEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 6:02 AM
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class AuditService {

    private final ApplicationContext appCtx;

    @SuppressWarnings("MethodMayBeStatic")
    @Async
    @EventListener
    @SneakyThrows
    public void processInitEvent(NewCompanyEvent event) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new IllegalStateException("Tx exists");
        }
        log.info("Company was created, name: {}, tx exists: {}", event.name, TransactionSynchronizationManager.isActualTransactionActive());

        log.info("All done. Start exiting an application");
        System.exit(SpringApplication.exit(appCtx, ()->0));
    }

}
