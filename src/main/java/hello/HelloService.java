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
        var context = new Observation.Context().put(String.class, "spring");
        var observation = Observation.start("service.hello", context, observationRegistry);
        try (var scope = observation.openScope()) {
            log.debug("Say hello");
            return "Hello!";
        }
        catch (Exception exception) {
            observation.error(exception);
            throw exception;
        }
        finally {
            observation.stop();
        }
    }
}
