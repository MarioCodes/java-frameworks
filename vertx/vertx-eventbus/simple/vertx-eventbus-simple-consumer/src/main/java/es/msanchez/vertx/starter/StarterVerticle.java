package es.msanchez.vertx.starter;

import es.msanchez.vertx.address.EventBusAddresses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarterVerticle extends AbstractVerticle {
	@Override
	public void start() {
		EventBus bus = vertx.eventBus();

		bus.consumer(EventBusAddresses.STRING_ADDRESS.toString(), handler -> {
			String message = handler.body().toString();
			log.info("Received message {}", message);
		});
	}
}
