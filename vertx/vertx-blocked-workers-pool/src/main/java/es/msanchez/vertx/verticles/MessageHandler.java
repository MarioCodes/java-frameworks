package es.msanchez.vertx.verticles;

import es.msanchez.vertx.addresses.EventBusAddress;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component @Slf4j public class MessageHandler extends AbstractVerticleHandler<String> {

  @Override protected String handleMessageBody(final Message<String> event) {
    log.info(String.format("FinalHandler called with parameter: %s", event.body()));
    return null;
  }

  @Override protected EventBusAddress eventBusAddress() {
    return EventBusAddress.MESSAGE_ADDRESS;
  }
}
