package es.msanchez.vertx.handler;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageHandler implements Handler<Message<String>> {

	@Override
	public void handle(final Message<String> event) {
		log.info("Received message: {} from through EventBus", event.body());
	}

}
