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

	@Override
	public void start() {
		final SpringRegister register = new SpringRegister();
		final AnnotationConfigApplicationContext context = register.iniSpringConfig(SpringConfig.class);
		final EventBus bus = context.getBean(EventBus.class);

		this.vertx.setPeriodic(1000, event -> {
			final String message = "oh no!";
			bus.send(EventBusAddresses.STRING_ADDRESS.toString(), message);
			log.info("Java verticle. Sent message: {} to EventBus", message);
		});
	}
}
