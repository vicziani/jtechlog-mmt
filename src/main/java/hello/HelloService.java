package hello;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    private ObservationRegistry observationRegistry;

    public HelloService(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }

    public String hello() {
        return Observation.createNotStarted("service.hello", observationRegistry)
                .lowCardinalityKeyValue("framework", "spring")
                .observe(() -> {
                    log.debug("Say hello");
                    return "Hello!";
                });
    }
}
