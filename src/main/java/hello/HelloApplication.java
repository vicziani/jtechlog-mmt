package hello;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.micrometer.observation.aop.ObservedAspect;
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

	@Bean
	ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
		return new ObservedAspect(observationRegistry);
	}

	@GetMapping("/")
	@Observed(name = "controller.hello", contextualName = "controller.hello", lowCardinalityKeyValues = {"framework", "spring"})
	public String hello() {
//		return Observation.createNotStarted("controller.hello", observationRegistry)
//				.lowCardinalityKeyValue("framework", "spring")
//				.observe(() -> {
//					return helloService.hello();
//				});

		return helloService.hello();
	}

}
