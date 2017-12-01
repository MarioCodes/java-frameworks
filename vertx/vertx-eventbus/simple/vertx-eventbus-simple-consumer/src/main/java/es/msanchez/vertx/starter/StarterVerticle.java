package es.msanchez.vertx.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.msanchez.vertx.address.EventBusAddresses;
import es.msanchez.vertx.config.SpringConfig;
import es.msanchez.vertx.handler.MessageHandler;
import es.msanchez.vertx.utilities.SpringRegister;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class StarterVerticle extends AbstractVerticle {

	private AnnotationConfigApplicationContext context;

	private SpringRegister register = new SpringRegister();

	private MessageHandler handler;

	@Override
	public void start() {
		context = register.iniSpringConfig(SpringConfig.class);
		handler = context.getBean(MessageHandler.class);
		EventBus bus = context.getBean(EventBus.class);

		bus.consumer(EventBusAddresses.STRING_ADDRESS.toString(), handler);
	}
}
