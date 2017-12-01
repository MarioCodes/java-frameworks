package es.msanchez.vertx.handler;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandler implements Handler<Message<String>> {

	@Override
	public void handle(Message<String> event) {
		log.info("Received message {}", event.body().toString());
	}

}
