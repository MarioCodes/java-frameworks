package es.msanchez.vertx.verticles;

import es.msanchez.vertx.addresses.EventBusAddress;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j @Component public class BlockingHandler extends AbstractVerticleHandler<String> {

  @Override protected String handleMessageBody(final Message<String> body) {
    log.info("Blocking handler called with message: {}", body.body());
    return null;
  }

  @Override protected EventBusAddress eventBusAddress() {
    return EventBusAddress.MESSAGE_ADDRESS;
  }
}
