package es.msanchez.vertx.verticles;

import es.msanchez.vertx.addresses.EventBusAddress;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j @Component public class ProducerVerticle extends AbstractVerticle {

  @Autowired private EventBus eventBus;

  @Override public void start()
      throws Exception {
    super.start();

    log.info("Called producer verticle.");

    for (int i = 0; i < 5; i++) {
      this.eventBus.send(EventBusAddress.MESSAGE_ADDRESS.toString(), "oh no!");
      log.info("Sent message");
      Thread.sleep(1000);
    }
  }

  @Override public void stop()
      throws Exception {
    super.stop();
  }
}
