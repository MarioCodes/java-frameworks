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

		vertx.setPeriodic(1000, event -> {
			bus.send(EventBusAddresses.STRING_ADDRESS.toString(), "producer");
			log.info("Sent message {}", "producer");
		});
	}
}
