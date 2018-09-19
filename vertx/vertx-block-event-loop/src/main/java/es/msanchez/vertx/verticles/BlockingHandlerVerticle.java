package es.msanchez.vertx.verticles;

import es.msanchez.vertx.addresses.EventBusAddress;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j @Component public class BlockingHandlerVerticle extends AbstractVerticleHandler<String> {

  @Autowired private Vertx vertx;

  @Override protected String handleMessageBody(final Message<String> body) {
    this.vertx.executeBlocking(handler -> {
      try {
        log.info("Called handler with id: {} obtained message: {}", this.deploymentID(), body.body());
        Thread.sleep(5000L);
      } catch (InterruptedException ex) {

      }
    }, future -> log.info("The blocking code was executed correctly"));

    return null;
  }

  @Override protected EventBusAddress eventBusAddress() {
    return EventBusAddress.MESSAGE_ADDRESS;
  }
}
