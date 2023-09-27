package bug.ehcache3195;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 * @author Moritz Halbritter
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
class CLR {
    private final Globals properties;

    @PostConstruct
    public void init() {
        System.out.printf("CLR, properties.dispatcher.enabled: %s\n", properties.dispatcher.enabled);
    }
}
