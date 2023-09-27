package bug.ehcache3195;

import bug.ehcache3195.beans.Company;
import bug.ehcache3195.events.NewCompanyTxEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sergio Lissner
 * Date: 9/27/2023
 * Time: 6:00 AM
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CompanyTxService {

    private final EventsBoundedToTx eventsBoundedToTx;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void addCompany(String name) {
        log.info("adding a new company " + name);
        companyRepository.save(new Company(name));
        eventPublisher.publishEvent(new NewCompanyTxEvent(name));
    }
}
