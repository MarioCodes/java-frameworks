package es.msanchez.vertx.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.msanchez.vertx.address.EventBusAddresses;
import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.handler.MessageHandler;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class StarterVerticle extends AbstractVerticle {

	@Override
	public void start() {
		final SpringRegister register = new SpringRegister();
		final AnnotationConfigApplicationContext context = register.iniSpringConfig(SpringConfig.class);

		final MessageHandler handler = context.getBean(MessageHandler.class);
		final EventBus bus = context.getBean(EventBus.class);

		bus.consumer(EventBusAddresses.STRING_ADDRESS.toString(), handler);
	}
}
