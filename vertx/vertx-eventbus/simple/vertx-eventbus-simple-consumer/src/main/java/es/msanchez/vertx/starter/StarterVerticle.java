package es.msanchez.vertx.starter;

import es.msanchez.vertx.address.EventBusAddresses;
import es.msanchez.vertx.handler.MessageHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class StarterVerticle extends AbstractVerticle {
	MessageHandler handler = new MessageHandler();

	@Override
	public void start() {
		EventBus bus = vertx.eventBus();
		bus.consumer(EventBusAddresses.STRING_ADDRESS.toString(), handler);
	}
}
