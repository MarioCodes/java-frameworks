package es.msanchez.vertx.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.msanchez.vertx.address.EventBusAddresses;
import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarterVerticle extends AbstractVerticle {

	private AnnotationConfigApplicationContext context;

	private SpringRegister register = new SpringRegister();

	@Override
	public void start() {
		context = register.iniSpringConfig(SpringConfig.class);

		EventBus bus = context.getBean(EventBus.class);
		vertx.setPeriodic(1000, event -> {
			bus.send(EventBusAddresses.STRING_ADDRESS.toString(), "producer");
			log.info("Sent message {}", "producer");
		});
	}
}
