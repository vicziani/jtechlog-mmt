package hello;

import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import brave.propagation.ThreadLocalCurrentTraceContext;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	private ObservationRegistry observationRegistry;

	private HelloService helloService;

	public HelloApplication(ObservationRegistry observationRegistry, HelloService helloService) {
		this.observationRegistry = observationRegistry;
		this.helloService = helloService;
	}

	@GetMapping("/")
	// @Observed - hogy lehet beüzemelni?
	public String hello() {

		Observation observation = Observation.start("controller.hello", observationRegistry);
		try (Observation.Scope scope = observation.openScope()) {
			return helloService.hello();
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
