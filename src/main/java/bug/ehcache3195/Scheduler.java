package bug.ehcache3195;

import bug.ehcache3195.events.InitEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 5:44 AM
 */
@Service
@EnableScheduling
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class Scheduler {

    private final Globals globals;
    private final ApplicationEventPublisher eventPublisher;

    private boolean sent = false;

    @Scheduled(initialDelay = 1_000, fixedDelay = 15_000_000)
    public void registerInternalTasks() {
        if (sent) {
            return;
        }
        log.info("Send InitEvent");
        eventPublisher.publishEvent(new InitEvent());
        sent = true;
    }
}
