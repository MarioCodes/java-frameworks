package es.msanchez.vertx.verticles;

import es.msanchez.vertx.addresses.EventBusAddress;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j @Component public class BlockingHandlerVerticle extends AbstractVerticleHandler<String> {

  @Override protected String handleMessageBody(final Message<String> body) {
    log.info("Called handler with id: {} obtained message: {}", this.deploymentID(), body.body());
    return null;
  }

  @Override protected EventBusAddress eventBusAddress() {
    return EventBusAddress.MESSAGE_ADDRESS;
  }
}
