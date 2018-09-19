package es.msanchez.vertx.verticles;

import es.msanchez.vertx.addresses.EventBusAddress;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j @Component public class ProducerVerticle extends AbstractVerticle {

  @Autowired private Vertx vertx;

  @Override public void start()
      throws Exception {
    super.start();
    log.info("Started producer verticle.");
    this.producePeriodic(1000L);
  }

  private void producePeriodic(
      final long sleep) {
    this.vertx.setPeriodic(sleep, id -> {
      final String message = String.valueOf(ThreadLocalRandom.current().nextInt());
      this.vertx.eventBus().publish(EventBusAddress.MESSAGE_ADDRESS.toString(), message);
    });
  }

  @Override public void stop()
      throws Exception {
    super.stop();
  }
}
