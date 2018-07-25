package es.msanchez.vertx.verticles;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author msanchez
 */
@Component @Slf4j public class MessageHandler implements Handler<Message<String>> {

  @Override public void handle(final Message<String> event) {
    log.info(String.format("MessageHandler called with parameter: %s", event.body()));
  }
}
