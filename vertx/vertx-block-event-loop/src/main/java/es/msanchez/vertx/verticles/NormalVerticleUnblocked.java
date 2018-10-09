package es.msanchez.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NormalVerticleUnblocked extends AbstractVerticle {

  @Autowired
  private Vertx vertx;

  @Override
  public void start() {
    log.info("Started NormalVerticleUnblocked.");
    this.vertx.executeBlocking(handler -> {
      try {
        Thread.sleep(5000L);
        handler.complete();
      } catch (InterruptedException ex) {
        handler.fail("Unblocked normal verticle. Error on Thread sleep");
      }
    }, future -> {
      if(future.succeeded())
        log.info("Ended NormalVerticleUnblocked.");
      else
        log.error(future.cause().getMessage());
    });
  }
}
