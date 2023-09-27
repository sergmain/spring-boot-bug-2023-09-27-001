package bug.ehcache3195;

import bug.ehcache3195.events.InitEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 6:00 AM
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CompanyService {

    private final CompanyTxService companyTxService;

    @Async
    @EventListener
    @SneakyThrows
    public void processInitEvent(InitEvent event) {
        companyTxService.addCompany("company-" + UUID.randomUUID());
    }
}
